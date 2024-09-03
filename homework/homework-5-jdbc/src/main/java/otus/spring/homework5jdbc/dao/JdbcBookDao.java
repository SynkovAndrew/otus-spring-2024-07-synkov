package otus.spring.homework5jdbc.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import otus.spring.homework5jdbc.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcBookDao implements BookDao {
    private static final String SELECT_BOOK_SQL = """
            SELECT b.id AS bookId, 
                   b.title AS title, 
                   g.id AS genreId, 
                   g.name AS genreName, 
            FROM book b 
            JOIN genre g ON g.id = b.id 
            LEFT JOIN book_to_author ba ON b.id = ba.book_id 
            LEFT JOIN author a ON a.id = ba.author_id 
            """;

    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public Book create(CreateBookContext context) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        var params = new HashMap<String, Object>();
        params.put("title", context.title());
        params.put("genreId", context.genre().id());

        jdbcOperations.update(
                "INSERT INTO book(title, genre_id) VALUES (:title, :genreId)",
                new MapSqlParameterSource(params),
                keyHolder
        );

        var bookId = keyHolder.getKeyAs(Long.class);

        context.authors().forEach(author -> {
                    var bookToAuthorParams = new HashMap<String, Object>();
                    bookToAuthorParams.put("authorId", author.id());
                    bookToAuthorParams.put("bookId", bookId);

                    jdbcOperations.update(
                            "INSERT INTO book_to_author(book_id, author_id) VALUES (:bookId, :authorId)",
                            bookToAuthorParams
                    );
                }
        );

        return new Book(
                keyHolder.getKeyAs(Long.class),
                context.title(),
                context.authors(),
                context.genre()
        );
    }

    @Override
    public Optional<Book> findById(Long id) {
        try {
            return Optional.ofNullable(
                    jdbcOperations.queryForObject(
                            SELECT_BOOK_SQL + " WHERE b.id = :id",
                            Collections.singletonMap("id", id),
                            new BookRowMapper()
                    )
            );
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public List<Book> findAll() {
        return jdbcOperations.query(SELECT_BOOK_SQL, new BookRowMapper());
    }

    @Override
    public Book update(Book book) {
        var params = new HashMap<String, Object>();
        params.put("title", book.title());
        params.put("id", book.id());

        var result = jdbcOperations.update(
                "UPDATE book SET title = :title WHERE id = :id",
                params
        );

        if (result == 0) {
            throw new EntityNotFoundException("Book(id=" + book.id() + ") is not found");
        }

        return book;
    }

    @Override
    public Book delete(Book book) {
        var result = jdbcOperations.update(
                "DELETE FROM book WHERE id = :id",
                Collections.singletonMap("id", book.id())
        );

        if (result == 0) {
            throw new EntityNotFoundException("Book(id=" + book.id() + ") is not found");
        }
        return book;
    }

    private static class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return null;/*new Book(
                    rs.getLong("id"),
                    rs.getString("name")
            );*/
        }
    }
}
