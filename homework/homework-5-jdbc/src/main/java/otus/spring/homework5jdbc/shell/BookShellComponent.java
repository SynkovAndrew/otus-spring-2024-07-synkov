package otus.spring.homework5jdbc.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import otus.spring.homework5jdbc.dao.AuthorDao;
import otus.spring.homework5jdbc.dao.BookDao;
import otus.spring.homework5jdbc.dao.GenreDao;

import java.util.Arrays;

@ShellComponent
@RequiredArgsConstructor
public class BookShellComponent {
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @ShellMethod(key = "create-book")
    public String createBook(
            @ShellOption(value = {"t", "title"}) String title,
            @ShellOption(value = {"g", "genre"}) Long genreId,
            @ShellOption(value = {"a", "authors"}) Long[] authorIds
    ) {
        var genre = genreDao.findById(genreId);
        var authors = Arrays.stream(authorIds).map(authorDao::findById).toList();
        var book = bookDao.create(new BookDao.CreateBookContext(title, authors, genre));

       return book.toString() + " created!";
    }
}
