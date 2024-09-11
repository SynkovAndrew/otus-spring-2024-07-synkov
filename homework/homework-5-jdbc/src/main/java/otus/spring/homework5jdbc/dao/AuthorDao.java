package otus.spring.homework5jdbc.dao;

import otus.spring.homework5jdbc.domain.Author;

import java.util.List;

public interface AuthorDao {

    Author create(CreateAuthorContext context);

    Author findById(Long id);

    List<Author> findAll();

    Author update(Author author);

    Author delete(Author author);

    record CreateAuthorContext(String firstName, String lastName) {}
}
