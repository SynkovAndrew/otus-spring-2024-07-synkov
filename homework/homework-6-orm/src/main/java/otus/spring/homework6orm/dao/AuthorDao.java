package otus.spring.homework6orm.dao;

import otus.spring.homework6orm.domain.Author;

import java.util.List;

public interface AuthorDao {

    Author create(Author author);

    Author findById(Long id);

    List<Author> findAll();

    Author update(Author author);

    Author delete(Author author);
}
