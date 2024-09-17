package otus.spring.homework6orm.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import otus.spring.homework6orm.dao.AuthorDao;
import otus.spring.homework6orm.domain.Author;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShellComponent {
    private static final String SHELL_METHOD_GROUP = "author";
    
    private final AuthorDao authorDao;

    @ShellMethod(key = "create-author", group = SHELL_METHOD_GROUP)
    public String createAuthor(
            @ShellOption(value = {"fn", "first-name"}) String firstName,
            @ShellOption(value = {"ln", "last-name"}) String lastName
    ) {
       return authorDao.create(new AuthorDao.CreateAuthorContext(firstName, lastName)).toString() + " created!";
    }

    @ShellMethod(key = "update-author", group = SHELL_METHOD_GROUP)
    public String updateAuthor(
            @ShellOption(value = {"id"}) Long id,
            @ShellOption(value = {"fn", "first-name"}) String firstName,
            @ShellOption(value = {"ln", "last-name"}) String lastName
    ) {
        return authorDao.update(new Author(id, firstName, lastName)).toString() + " updated!";
    }

    @ShellMethod(key = "find-author", group = SHELL_METHOD_GROUP)
    public String findAuthorById(@ShellOption(value = {"id"}) Long id) {
        return authorDao.findById(id) + " found!";
    }

    @ShellMethod(key = "find-all-authors", group = SHELL_METHOD_GROUP)
    public String findAllAuthors() {
        return authorDao.findAll() + " found!";
    }

    @ShellMethod(key = "delete-author", group = SHELL_METHOD_GROUP)
    public String deleteAuthorById(@ShellOption(value = {"id"}) Long id) {
        return authorDao.delete(authorDao.findById(id)) + " deleted!";
    }
}
