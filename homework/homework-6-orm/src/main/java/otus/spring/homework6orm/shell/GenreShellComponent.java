package otus.spring.homework6orm.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import otus.spring.homework6orm.dao.GenreDao;
import otus.spring.homework6orm.domain.Genre;

@ShellComponent
@RequiredArgsConstructor
public class GenreShellComponent {
    private static final String SHELL_METHOD_GROUP = "genre";

    private final GenreDao genreDao;

    @ShellMethod(key = "create-genre", group = SHELL_METHOD_GROUP)
    public String createGenre(@ShellOption(value = {"n", "name"}) String name) {
        return genreDao.create(new GenreDao.CreateGenreContext(name)).toString() + " created!";
    }

    @ShellMethod(key = "update-genre", group = SHELL_METHOD_GROUP)
    public String updateGenre(
            @ShellOption(value = {"id"}) Long id,
            @ShellOption(value = {"n", "name"}) String name
    ) {
        return genreDao.update(new Genre(id, name)).toString() + " updated!";
    }

    @ShellMethod(key = "find-genre", group = SHELL_METHOD_GROUP)
    public String findGenreById(@ShellOption(value = {"id"}) Long id) {
        return genreDao.findById(id) + " found!";
    }

    @ShellMethod(key = "find-all-genres", group = SHELL_METHOD_GROUP)
    public String findAllGenres() {
        return genreDao.findAll() + " found!";
    }

    @ShellMethod(key = "delete-genre", group = SHELL_METHOD_GROUP)
    public String deleteGenreById(@ShellOption(value = {"id"}) Long id) {
        return genreDao.delete(genreDao.findById(id)) + " deleted!";
    }
}
