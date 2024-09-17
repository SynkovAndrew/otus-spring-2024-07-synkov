package otus.spring.homework6orm.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import otus.spring.homework6orm.dao.AuthorDao;
import otus.spring.homework6orm.dao.BookDao;
import otus.spring.homework6orm.dao.GenreDao;
import otus.spring.homework6orm.domain.Book;

import java.util.Arrays;

@ShellComponent
@RequiredArgsConstructor
public class BookShellComponent {
    private static final String SHELL_METHOD_GROUP = "book";

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @ShellMethod(key = "create-book", group = SHELL_METHOD_GROUP)
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

    @ShellMethod(key = "update-book", group = SHELL_METHOD_GROUP)
    public String updateBook(
            @ShellOption(value = {"id"}) Long id,
            @ShellOption(value = {"t", "title"}) String title,
            @ShellOption(value = {"g", "genre"}) Long genreId,
            @ShellOption(value = {"a", "authors"}) Long[] authorIds
    ) {
        var genre = genreDao.findById(genreId);
        var authors = Arrays.stream(authorIds).map(authorDao::findById).toList();

        return bookDao.update(new Book(id, title, authors, genre)).toString() + " updated!";
    }

    @ShellMethod(key = "find-book", group = SHELL_METHOD_GROUP)
    public String findBookById(@ShellOption(value = {"id"}) Long id) {
        return bookDao.findById(id) + " found!";
    }

    @ShellMethod(key = "find-all-books", group = SHELL_METHOD_GROUP)
    public String findAllBooks() {
        return bookDao.findAll() + " found!";
    }

    @ShellMethod(key = "delete-book", group = SHELL_METHOD_GROUP)
    public String deleteBookById(@ShellOption(value = {"id"}) Long id) {
        return bookDao.delete(bookDao.findById(id)) + " deleted!";
    }
}
