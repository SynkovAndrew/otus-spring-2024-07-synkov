package otus.spring.homework5jdbc.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import otus.spring.homework5jdbc.DatabasePreparer;
import otus.spring.homework5jdbc.domain.Genre;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import({JdbcGenreDao.class, DatabasePreparer.class})
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
                .isEqualTo(Optional.of(genre));
    }

    @Test
    public void updateGenre() {
        var genre = genreDao.create(new GenreDao.CreateGenreContext("Novel"));

        genreDao.update(new Genre(genre.id(), "Fantastic"));

        assertThat(genreDao.findById(genre.id()))
                .usingRecursiveComparison()
                .isEqualTo(
                        Optional.of(
                                new Genre(genre.id(), "Fantastic")
                        )
                );
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

        assertThat(genreDao.findById(genre.id()))
                .usingRecursiveComparison()
                .isEqualTo(Optional.empty());
    }

    @Test
    public void findGenreByIdWhenNotExists() {
        assertThat(genreDao.findById(999L))
                .usingRecursiveComparison()
                .isEqualTo(Optional.empty());
    }
}
