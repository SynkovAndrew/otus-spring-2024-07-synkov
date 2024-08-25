package otus.spring.homework4springshell.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import otus.spring.homework4springshell.domain.Student;
import otus.spring.homework4springshell.service.StudentHolder;

@ShellComponent
@RequiredArgsConstructor
public class LoginShellComponent {
    private final StudentHolder studentHolder;

    @ShellMethod(key = "login")
    public String login(
            @ShellOption(value = {"fn", "firstName"}) String firstName,
            @ShellOption(value = {"ln", "lastName"}) String lastName
    ) {
        if (firstName == null || firstName.isBlank()) {
            return "First name is not provided";
        }

        if (lastName == null || lastName.isBlank()) {
            return "Last name is not provided";
        }

        studentHolder.set(new Student(firstName, lastName));

        return "Logged in successfully";
    }
}
