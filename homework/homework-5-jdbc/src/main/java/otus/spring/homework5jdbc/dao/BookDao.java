package otus.spring.homework5jdbc.dao;

import otus.spring.homework5jdbc.domain.Book;

import java.util.List;

public interface BookDao {

    Book create(Book book);

    Book findById(Long id);
    
    List<Book> findAll();

    Book update(Book book);

    Book deleteById(Long id);
}
