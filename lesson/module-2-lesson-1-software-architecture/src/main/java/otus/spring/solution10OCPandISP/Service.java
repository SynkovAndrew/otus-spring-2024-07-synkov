package otus.spring.solution10OCPandISP;


import otus.spring.solution10OCPandISP.config.AppSettings;
import otus.spring.solution10OCPandISP.services.ApplicationRunner;
import otus.spring.solution10OCPandISP.services.ApplicationStopServiceImpl;
import otus.spring.solution10OCPandISP.services.IOServiceStreams;
import otus.spring.solution10OCPandISP.services.NoteConverterImpl;
import otus.spring.solution10OCPandISP.services.NotesServiceImpl;
import otus.spring.solution10OCPandISP.services.menu.MenuOption;
import otus.spring.solution10OCPandISP.services.menu.MenuOptionsRegistryImpl;
import otus.spring.solution10OCPandISP.services.processors.AddNewNoteSingleCommandProcessor;
import otus.spring.solution10OCPandISP.services.processors.DeleteNoteSingleCommandProcessor;
import otus.spring.solution10OCPandISP.services.processors.MenuCommandsProcessorImpl;
import otus.spring.solution10OCPandISP.services.processors.ShowAllNotesSingleCommandProcessor;
import otus.spring.solution10OCPandISP.services.processors.StopApplicationSingleCommandProcessor;
import otus.spring.solution10OCPandISP.services.processors.UpdateNoteSingleCommandProcessor;

import java.util.List;

// OCP + ISP (AppSettings + IOService) + тесты
public class Service {

    public void run () {
        var appSettings = new AppSettings(true, "dd.mm.YYYY HH:mm:ss");
        var ioService = new IOServiceStreams(System.out, System.in);
        var applicationStopService = new ApplicationStopServiceImpl(ioService, appSettings);
        var notesService = new NotesServiceImpl();
        var noteConverter = new NoteConverterImpl(appSettings);

        var showAllNotesMenuOption = new MenuOption(1, "Вывести все заметки");
        var addNewNoteMenuOption = new MenuOption(2, "Добавить заметку");
        var updateNoteMenuOption = new MenuOption(3, "Изменить заметку");
        var deleteNoteMenuOption = new MenuOption(4, "Удалить заметку");
        var stopApplicationMenuOption = new MenuOption(5, "Выйти");

        var menuOptions = List.of(showAllNotesMenuOption, addNewNoteMenuOption,
                updateNoteMenuOption, deleteNoteMenuOption, stopApplicationMenuOption
        );
        var menuOptionsRegistry = new MenuOptionsRegistryImpl(menuOptions);

        var processors = List.of(
                new ShowAllNotesSingleCommandProcessor(ioService, notesService, noteConverter, showAllNotesMenuOption),
                new AddNewNoteSingleCommandProcessor(ioService, notesService, addNewNoteMenuOption),
                new UpdateNoteSingleCommandProcessor(ioService, notesService, updateNoteMenuOption),
                new DeleteNoteSingleCommandProcessor(ioService, notesService, deleteNoteMenuOption),
                new StopApplicationSingleCommandProcessor(applicationStopService, stopApplicationMenuOption)
        );

        var menuCommandsProcessor = new MenuCommandsProcessorImpl(processors);

        new ApplicationRunner(ioService, applicationStopService, menuOptionsRegistry, menuCommandsProcessor)
                .run();
    }
}
