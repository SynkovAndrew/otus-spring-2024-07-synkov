package otus.spring.solution9DIP;


import otus.spring.solution9DIP.config.AppSettings;
import otus.spring.solution9DIP.services.ApplicationRunner;
import otus.spring.solution9DIP.services.ApplicationStopServiceImpl;
import otus.spring.solution9DIP.services.MenuCommandsProcessorImpl;
import otus.spring.solution9DIP.services.NoteConverterImpl;
import otus.spring.solution9DIP.services.NotesServiceImpl;
import otus.spring.solution9DIP.services.StreamsIOService;

// ApplicationRunner, MenuCommandsProcessor, NotesService
public class Service {

    public void run () {
        var appSettings = new AppSettings(true, "dd.mm.YYYY HH:mm:ss");
        var ioService = new StreamsIOService(System.out, System.in);
        var applicationStopService = new ApplicationStopServiceImpl(ioService, appSettings);
        var notesService = new NotesServiceImpl();
        var noteConverter = new NoteConverterImpl(appSettings);
        var menuCommandsProcessor = new MenuCommandsProcessorImpl(ioService, notesService,
                noteConverter, applicationStopService);

        ApplicationRunner applicationRunner = new ApplicationRunner(
                ioService,
                applicationStopService,
                menuCommandsProcessor
        );
        applicationRunner.run();
    }
}
