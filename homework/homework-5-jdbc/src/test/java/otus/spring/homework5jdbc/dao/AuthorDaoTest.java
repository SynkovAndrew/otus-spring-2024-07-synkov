package otus.spring.homework5jdbc.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import otus.spring.homework5jdbc.DatabasePreparer;
import otus.spring.homework5jdbc.domain.Author;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import({JdbcAuthorDao.class, DatabasePreparer.class})
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
        var author = authorDao.create(new AuthorDao.CreateAuthorContext("Lev", "Tolstoy"));

        assertThat(authorDao.findById(author.id()))
                .usingRecursiveComparison()
                .isEqualTo(Optional.of(author));
    }

    @Test
    public void updateAuthor() {
        var author = authorDao.create(new AuthorDao.CreateAuthorContext("Lev", "Tolstoy"));

        authorDao.update(new Author(author.id(), "Ivan", "Nikolaev"));

        assertThat(authorDao.findById(author.id()))
                .usingRecursiveComparison()
                .isEqualTo(
                        Optional.of(
                                new Author(author.id(), "Ivan", "Nikolaev")
                        )
                );
    }
}
