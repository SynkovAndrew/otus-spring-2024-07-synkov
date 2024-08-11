package otus.spring.solution10OCPandISP.services.processors;


import otus.spring.solution10OCPandISP.services.NoteConverter;
import otus.spring.solution10OCPandISP.services.NotesService;
import otus.spring.solution10OCPandISP.services.menu.MenuOption;

import java.util.stream.IntStream;

public class ShowAllNotesSingleCommandProcessor implements MenuSingleCommandProcessor {
    private final MenuOption processedCommandOption;

    private final OutputService outputService;
    private final NotesService notesService;
    private final NoteConverter noteConverter;

    public ShowAllNotesSingleCommandProcessor(OutputService outputService, NotesService notesService,
                                              NoteConverter noteConverter, MenuOption processedCommandOption) {
        this.outputService = outputService;
        this.notesService = notesService;
        this.noteConverter = noteConverter;
        this.processedCommandOption = processedCommandOption;
    }

    @Override
    public void processCommand() {
        var notes = notesService.getAll();
        outputService.outputString("Заметки:");
        IntStream.range(1, notes.size() + 1)
                .mapToObj(k -> noteConverter.convertNoteToString(k, notes.get(k - 1)))
                .forEach(outputService::outputString);
        outputService.outputString("");
    }

    @Override
    public MenuOption getProcessedCommandOption() {
        return processedCommandOption;
    }
}
