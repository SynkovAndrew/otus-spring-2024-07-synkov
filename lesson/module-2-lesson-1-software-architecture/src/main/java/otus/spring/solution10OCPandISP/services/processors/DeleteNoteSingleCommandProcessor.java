package otus.spring.solution10OCPandISP.services.processors;


import otus.spring.solution10OCPandISP.services.NotesService;
import otus.spring.solution10OCPandISP.services.menu.MenuOption;

import static otus.spring.solution10OCPandISP.services.processors.utils.NotesListUtil.checkNoteNumber;

public class DeleteNoteSingleCommandProcessor implements MenuSingleCommandProcessor {
    private final MenuOption processedCommandOption;
    private final InputService inputService;
    private final NotesService notesService;

    public DeleteNoteSingleCommandProcessor(InputService inputService, NotesService notesService,
                                            MenuOption processedCommandOption) {
        this.inputService = inputService;
        this.notesService = notesService;
        this.processedCommandOption = processedCommandOption;
    }

    @Override
    public void processCommand() {
        var notes = notesService.getAll();

        var deletedNoteNumber = inputService.readIntWithPrompt("Введите номер удаляемой заметки...");
        checkNoteNumber(deletedNoteNumber, notes.size());

        var updatedNote = notes.get(deletedNoteNumber - 1);
        notesService.remove(updatedNote.getId());
    }

    @Override
    public MenuOption getProcessedCommandOption() {
        return processedCommandOption;
    }

}
