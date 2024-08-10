package otus.spring.solution8SRP;

import otus.spring.solution7SRP.exceptions.MenuItemIndexOutOfBoundsException;
import otus.spring.solution7SRP.exceptions.NoteIndexOutOfBoundsException;
import otus.spring.solution7SRP.model.Note;
import otus.spring.solution7SRP.service.ConsoleIOService;
import otus.spring.solution7SRP.service.NoteConverter;
import otus.spring.solution8SRP.services.ApplicationRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

// ApplicationRunner, MenuCommandsProcessor, NotesService
public class Service {

    public void run () {
        ApplicationRunner applicationRunner = new ApplicationRunner();
        applicationRunner.run();
    }
}
