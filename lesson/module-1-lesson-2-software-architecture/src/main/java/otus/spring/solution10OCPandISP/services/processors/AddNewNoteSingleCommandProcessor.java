package otus.spring.solution10OCPandISP.services.processors;


import otus.spring.solution10OCPandISP.model.Note;
import otus.spring.solution10OCPandISP.services.NotesService;
import otus.spring.solution10OCPandISP.services.menu.MenuOption;

public class AddNewNoteSingleCommandProcessor implements MenuSingleCommandProcessor {
    private final MenuOption processedCommandOption;
    private final InputService inputService;
    private final NotesService notesService;

    public AddNewNoteSingleCommandProcessor(InputService inputService, NotesService notesService,
                                            MenuOption processedCommandOption) {
        this.inputService = inputService;
        this.notesService = notesService;
        this.processedCommandOption = processedCommandOption;
    }

    @Override
    public void processCommand() {
        var noteText = inputService.readStringWithPrompt("Введите текст заметки...");
        notesService.save(Note.of(noteText));

    }

    @Override
    public MenuOption getProcessedCommandOption() {
        return processedCommandOption;
    }
}
