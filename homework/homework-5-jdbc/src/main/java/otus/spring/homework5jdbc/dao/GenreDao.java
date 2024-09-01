package otus.spring.homework5jdbc.dao;

import otus.spring.homework5jdbc.domain.Genre;

import java.util.List;

public interface GenreDao {

    Genre create(Genre genre);

    Genre findById(Long id);

    List<Genre> findAll();

    Genre update(Genre genre);

    Genre deleteById(Long id);
}
