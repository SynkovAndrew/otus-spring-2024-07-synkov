package otus.spring.homework5jdbc.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import otus.spring.homework5jdbc.DatabasePreparer;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import({JdbcBookDao.class, JdbcAuthorDao.class, JdbcGenreDao.class, DatabasePreparer.class})
public class BookDaoTest {
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final DatabasePreparer databasePreparer;

    @Autowired
    public BookDaoTest(
            BookDao bookDao,
            AuthorDao authorDao,
            GenreDao genreDao,
            DatabasePreparer databasePreparer
    ) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.databasePreparer = databasePreparer;
    }

    @BeforeEach
    @AfterEach
    public void cleanUp() {
        databasePreparer.clearBooks();
    }

/*    @Test
    public void createBook() {
        var book = bookDao.create(
                new BookDao.CreateBookContext("War and Peace", List.of(1L), 1L)
        );

        assertThat(bookDao.findById(book.id()))
                .usingRecursiveComparison()
                .isEqualTo(Optional.of(book));
    }

    @Test
    public void updateBook() {
        var book = bookDao.create(new BookDao.CreateBookContext("Lev", "Tolstoy"));

        bookDao.update(new Book(book.id(), "Ivan", "Nikolaev"));

        assertThat(bookDao.findById(book.id()))
                .usingRecursiveComparison()
                .isEqualTo(
                        Optional.of(
                                new Book(book.id(), "Ivan", "Nikolaev")
                        )
                );
    }*/

    @Test
    public void findAllBooks() throws SQLException {
        var author = authorDao.create(new AuthorDao.CreateAuthorContext("Lev", "Tolstoy"));
        var genre = genreDao.create(new GenreDao.CreateGenreContext("Novel"));
        var warAndPeace = bookDao.create(
                new BookDao.CreateBookContext("War and Peace", List.of(author), genre)
        );

        assertThat(bookDao.findAll())
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(Arrays.asList(warAndPeace));
    }

 /*   @Test
    public void deleteBook() {
        var book = bookDao.create(new BookDao.CreateBookContext("Lev", "Tolstoy"));

        bookDao.delete(book);

        assertThat(bookDao.findById(book.id()))
                .usingRecursiveComparison()
                .isEqualTo(Optional.empty());
    }*/

    @Test
    public void findBookByIdWhenNotExists() {
        assertThat(bookDao.findById(999L))
                .usingRecursiveComparison()
                .isEqualTo(Optional.empty());
    }
}
