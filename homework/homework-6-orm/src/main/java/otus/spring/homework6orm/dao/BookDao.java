package otus.spring.homework6orm.dao;

import otus.spring.homework6orm.domain.Author;
import otus.spring.homework6orm.domain.Book;
import otus.spring.homework6orm.domain.Genre;

import java.util.List;

public interface BookDao {

    Book create(CreateBookContext context);

    Book findById(Long id);
    
    List<Book> findAll();

    Book update(Book book);

    Book delete(Book book);

    record CreateBookContext(String title, List<Author> authors, Genre genre) {}

}
