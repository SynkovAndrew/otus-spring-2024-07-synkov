package otus.spring.homework5jdbc.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import otus.spring.homework5jdbc.dao.AuthorDao;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShellComponent {
    private final AuthorDao authorDao;

    @ShellMethod(key = "create-author")
    public String createAuthor(
            @ShellOption(value = {"n", "firstName"}) String firstName,
            @ShellOption(value = {"n", "lastName"}) String lastName
    ) {
       var author =  authorDao.create(new AuthorDao.CreateAuthorContext(firstName, lastName));

       return author.toString() + " created!";
    }
}
