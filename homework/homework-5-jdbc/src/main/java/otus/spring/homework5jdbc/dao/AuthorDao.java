package otus.spring.homework5jdbc.dao;

import otus.spring.homework5jdbc.domain.Author;

import java.util.List;

public interface AuthorDao {

    void create(Author author);

    Author findById(Long id);

    List<Author> findAll();

    void update(Author author);

    void deleteById(Long id);
}
