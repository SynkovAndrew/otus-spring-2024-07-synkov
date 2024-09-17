package otus.spring.homework6orm.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import otus.spring.homework6orm.DatabasePreparer;
import otus.spring.homework6orm.domain.Author;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@JdbcTest
@Import({JpaAuthorDao.class, DatabasePreparer.class})
public class AuthorDaoTest {
    private final AuthorDao authorDao;
    private final DatabasePreparer databasePreparer;

    @Autowired
    public AuthorDaoTest(AuthorDao authorDao, DatabasePreparer databasePreparer) {
        this.authorDao = authorDao;
        this.databasePreparer = databasePreparer;
    }

    @BeforeEach
    @AfterEach
    public void cleanUp() {
        databasePreparer.clearAuthors();
    }

    @Test
    public void createAuthor() {
        var author = authorDao.create(new Author(null, "Lev", "Tolstoy"));

        assertThat(authorDao.findById(author.getId()))
                .usingRecursiveComparison()
                .isEqualTo(author);
    }

    @Test
    public void updateAuthor() {
        var author = authorDao.create(new Author(null, "Lev", "Tolstoy"));

        authorDao.update(new Author(author.getId(), "Ivan", "Nikolaev"));

        assertThat(authorDao.findById(author.getId()))
                .usingRecursiveComparison()
                .isEqualTo(new Author(author.getId(), "Ivan", "Nikolaev"));
    }

    @Test
    public void findAllAuthors() {
        var tolstoy = authorDao.create(new AuthorDao.CreateAuthorContext("Lev", "Tolstoy"));
        var bunin = authorDao.create(new AuthorDao.CreateAuthorContext("Ivan", "Bunin"));
        var remark = authorDao.create(new AuthorDao.CreateAuthorContext("Erich", "Remark"));

        assertThat(authorDao.findAll())
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(Arrays.asList(tolstoy, bunin, remark));
    }

    @Test
    public void deleteAuthor() {
        var author = authorDao.create(new AuthorDao.CreateAuthorContext("Lev", "Tolstoy"));

        authorDao.delete(author);

        assertThatThrownBy(() -> authorDao.findById(author.id()))
                .isInstanceOf(DaoException.EntityNotFound.class)
                .hasMessage("Author(id=" + author.id() + ") is not found");
    }

    @Test
    public void findAuthorByIdWhenNotExists() {
        assertThatThrownBy(() -> authorDao.findById(999L))
                .isInstanceOf(DaoException.EntityNotFound.class)
                .hasMessage("Author(id=999) is not found");
    }
}
