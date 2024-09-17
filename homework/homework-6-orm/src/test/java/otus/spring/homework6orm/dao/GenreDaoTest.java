package otus.spring.homework6orm.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import otus.spring.homework6orm.DatabasePreparer;
import otus.spring.homework6orm.domain.Genre;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@JdbcTest
@Import({JpaGenreDao.class, DatabasePreparer.class})
public class GenreDaoTest {
    private final GenreDao genreDao;
    private final DatabasePreparer databasePreparer;

    @Autowired
    public GenreDaoTest(GenreDao genreDao, DatabasePreparer databasePreparer) {
        this.genreDao = genreDao;
        this.databasePreparer = databasePreparer;
    }

    @BeforeEach
    @AfterEach
    public void cleanUp() {
        databasePreparer.clearGenres();
    }

    @Test
    public void createGenre() {
        var genre = genreDao.create(new GenreDao.CreateGenreContext("Novel"));

        assertThat(genreDao.findById(genre.id()))
                .usingRecursiveComparison()
                .isEqualTo(genre);
    }

    @Test
    public void updateGenre() {
        var genre = genreDao.create(new GenreDao.CreateGenreContext("Novel"));

        genreDao.update(new Genre(genre.id(), "Fantastic"));

        assertThat(genreDao.findById(genre.id()))
                .usingRecursiveComparison()
                .isEqualTo(new Genre(genre.id(), "Fantastic"));
    }

    @Test
    public void findAllGenres() {
        var novel = genreDao.create(new GenreDao.CreateGenreContext("Novel"));
        var fantastic = genreDao.create(new GenreDao.CreateGenreContext("Fantastic"));
        var detective = genreDao.create(new GenreDao.CreateGenreContext("Detective"));

        assertThat(genreDao.findAll())
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(Arrays.asList(novel, fantastic, detective));
    }

    @Test
    public void deleteGenre() {
        var genre = genreDao.create(new GenreDao.CreateGenreContext("Novel"));

        genreDao.delete(genre);

        assertThatThrownBy(() -> genreDao.findById(genre.id()))
                .isInstanceOf(DaoException.EntityNotFound.class)
                .hasMessage("Genre(id="+ genre.id() + ") is not found");
    }

    @Test
    public void findGenreByIdWhenNotExists() {
        assertThatThrownBy(() -> genreDao.findById(999L))
                .isInstanceOf(DaoException.EntityNotFound.class)
                .hasMessage("Genre(id=999) is not found");
    }
}
