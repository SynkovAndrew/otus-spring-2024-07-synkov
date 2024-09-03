package otus.spring.homework5jdbc.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import otus.spring.homework5jdbc.dao.GenreDao;

@ShellComponent
@RequiredArgsConstructor
public class GenreShellComponent {
    private final GenreDao genreDao;

    @ShellMethod(key = "create-genre")
    public String createGenre(@ShellOption(value = {"n", "name"}) String name) {
       var genre =  genreDao.create(new GenreDao.CreateGenreContext(name));

       return genre.toString() + " created!";
    }
}
