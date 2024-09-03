package otus.spring.homework5jdbc.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import otus.spring.homework5jdbc.DatabasePreparer;
import otus.spring.homework5jdbc.domain.Author;
import otus.spring.homework5jdbc.domain.Book;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    public void updateBook() {
        var levTolstoy = authorDao.create(new AuthorDao.CreateAuthorContext("Lev", "Tolstoy"));
        var ivanBunin = authorDao.create(new AuthorDao.CreateAuthorContext("Ivan", "Bunin"));
        var petrNaumov = authorDao.create(new AuthorDao.CreateAuthorContext("Petr", "Naumov"));
        var novel = genreDao.create(new GenreDao.CreateGenreContext("Novel"));
        var detective = genreDao.create(new GenreDao.CreateGenreContext("Detective"));
        var book = bookDao.create(
                new BookDao.CreateBookContext("War and Peace", List.of(levTolstoy, ivanBunin), novel)
        );

        bookDao.update(new Book(book.id(), "Black Swan", List.of(petrNaumov), detective));

        assertThat(bookDao.findById(book.id()))
                .usingRecursiveComparison()
                .isEqualTo(new Book(book.id(), "Black Swan", List.of(petrNaumov), detective));
    }

    @Test
    public void createAndFindBookById() {
        var levTolstoy = authorDao.create(new AuthorDao.CreateAuthorContext("Lev", "Tolstoy"));
        var ivanBunin = authorDao.create(new AuthorDao.CreateAuthorContext("Ivan", "Bunin"));
        var genre = genreDao.create(new GenreDao.CreateGenreContext("Novel"));
        var warAndPeace = bookDao.create(
                new BookDao.CreateBookContext("War and Peace", List.of(levTolstoy, ivanBunin), genre)
        );

        assertThat(bookDao.findById(warAndPeace.id()))
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(warAndPeace);
    }

    @Test
    public void createWhenAuthorNotExists() {
        var levTolstoy = new Author(1L, "Lev", "Tolstoy");
        var genre = genreDao.create(new GenreDao.CreateGenreContext("Novel"));
        var createBookContext = new BookDao.CreateBookContext(
                "War and Peace",
                List.of(levTolstoy),
                genre
        );

        assertThatThrownBy(() -> bookDao.create(createBookContext))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Author(id=1) is not found");
    }

    @Test
    public void findAllBooks() {
        var levTolstoy = authorDao.create(new AuthorDao.CreateAuthorContext("Lev", "Tolstoy"));
        var ivanBunin = authorDao.create(new AuthorDao.CreateAuthorContext("Ivan", "Bunin"));
        var petrNaumov = authorDao.create(new AuthorDao.CreateAuthorContext("Petr", "Naumov"));
        var novel = genreDao.create(new GenreDao.CreateGenreContext("Novel"));
        var detective = genreDao.create(new GenreDao.CreateGenreContext("Detective"));
        var warAndPeace = bookDao.create(
                new BookDao.CreateBookContext("War and Peace", List.of(levTolstoy, ivanBunin), novel)
        );
        var blackStreet = bookDao.create(
                new BookDao.CreateBookContext("Black Street", List.of(petrNaumov, ivanBunin), detective)
        );

        assertThat(bookDao.findAll())
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(Arrays.asList(warAndPeace, blackStreet));
    }

    @Test
    public void findAllBooksWhenNoExists() {
        assertThat(bookDao.findAll())
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(List.of());
    }

    @Test
    public void deleteBook() {
        var levTolstoy = authorDao.create(new AuthorDao.CreateAuthorContext("Lev", "Tolstoy"));
        var ivanBunin = authorDao.create(new AuthorDao.CreateAuthorContext("Ivan", "Bunin"));
        var genre = genreDao.create(new GenreDao.CreateGenreContext("Novel"));
        var book = bookDao.create(
                new BookDao.CreateBookContext("War and Peace", List.of(levTolstoy, ivanBunin), genre)
        );

        bookDao.delete(book);

        assertThatThrownBy(() -> bookDao.findById(book.id()))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Book(id=" + book.id() + ") is not found");
    }

    @Test
    public void findBookByIdWhenNotExists() {
        assertThatThrownBy(() -> bookDao.findById(999L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Book(id=999) is not found");
    }
}
