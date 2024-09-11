package otus.spring.homework5jdbc.dao;

import otus.spring.homework5jdbc.domain.Author;
import otus.spring.homework5jdbc.domain.Book;
import otus.spring.homework5jdbc.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    Book create(CreateBookContext context);

    Book findById(Long id);
    
    List<Book> findAll();

    Book update(Book book);

    Book delete(Book book);

    record CreateBookContext(String title, List<Author> authors, Genre genre) {}

}
