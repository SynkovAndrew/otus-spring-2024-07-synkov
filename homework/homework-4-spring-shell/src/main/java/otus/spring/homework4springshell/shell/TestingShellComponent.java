package otus.spring.homework4springshell.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import otus.spring.homework4springshell.service.StudentHolder;
import otus.spring.homework4springshell.service.TestRunnerService;

@ShellComponent
@RequiredArgsConstructor
public class TestingShellComponent {
    private final TestRunnerService testRunnerService;
    private final StudentHolder studentHolder;

    @ShellMethod(key = "test")
    @ShellMethodAvailability("testCommandAvailability")
    public void test() {
        testRunnerService.run();
    }

    public Availability testCommandAvailability() {
        return studentHolder.exists()
                ? Availability.available()
                : Availability.unavailable("\"You are not logged in. Please use login command.\"");
    }
}
