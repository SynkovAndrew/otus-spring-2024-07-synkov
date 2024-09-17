package otus.spring.homework6orm.dao;

import otus.spring.homework6orm.domain.Genre;

import java.util.List;

public interface GenreDao {

    Genre create(CreateGenreContext context);

    Genre findById(Long id);

    List<Genre> findAll();

    Genre update(Genre genre);

    Genre delete(Genre genre);

    record CreateGenreContext(String name) {}
}
