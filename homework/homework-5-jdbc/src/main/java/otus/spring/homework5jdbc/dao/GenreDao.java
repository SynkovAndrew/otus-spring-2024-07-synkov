package otus.spring.homework5jdbc.dao;

import otus.spring.homework5jdbc.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {

    Genre create(CreateGenreContext context);

    Genre findById(Long id);

    List<Genre> findAll();

    Genre update(Genre genre);

    Genre delete(Genre genre);

    record CreateGenreContext(String name) {}
}
