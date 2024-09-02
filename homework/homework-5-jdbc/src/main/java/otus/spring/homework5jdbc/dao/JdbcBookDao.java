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
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcBookDao implements BookDao {
    private static final String SELECT_BOOK_SQL = "SELECT id, name FROM book";

    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public Book create(CreateBookContext context) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        var params = new HashMap<String, Object>();
        params.put("name", context.name());

        jdbcOperations.update(
                "INSERT INTO book(name) VALUES (:name)",
                new MapSqlParameterSource(params),
                keyHolder
        );

        return new Book(keyHolder.getKeyAs(Long.class), context.name());
    }

    @Override
    public Optional<Book> findById(Long id) {
        try {
            return Optional.ofNullable(
                    jdbcOperations.queryForObject(
                            SELECT_GENRE_SQL + " WHERE id = :id",
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
        return jdbcOperations.query(SELECT_GENRE_SQL, new BookRowMapper());
    }

    @Override
    public Book update(Book book) {
        var params = new HashMap<String, Object>();
        params.put("name", book.name());
        params.put("id", book.id());

        var result = jdbcOperations.update(
                "UPDATE book SET name = :name WHERE id = :id",
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
            return new Book(
                    rs.getLong("id"),
                    rs.getString("name")
            );
        }
    }
}
