package otus.spring.homework5jdbc.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import otus.spring.homework5jdbc.domain.Author;
import otus.spring.homework5jdbc.domain.Book;
import otus.spring.homework5jdbc.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class JdbcBookDao implements BookDao {
    private static final String SELECT_BOOK_SQL = """
            SELECT b.id AS bookId, 
                   b.title AS bookTitle, 
                   g.id AS genreId, 
                   g.name AS genreName, 
                   a.id AS authorId,
                   a.first_name AS authorFirstName,
                   a.last_name AS authorLastName,
            FROM book b 
            JOIN genre g ON g.id = b.genre_id
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

        insertAuthors(context.authors(), bookId);

        return new Book(
                bookId,
                context.title(),
                context.authors(),
                context.genre()
        );
    }

    @Override
    public Book findById(Long id) {
            var bookReadEntities = jdbcOperations.query(
                    SELECT_BOOK_SQL + " WHERE b.id = :id",
                    Collections.singletonMap("id", id),
                    new BookReadEntityRowMapper()
            );

            if (bookReadEntities.isEmpty()) {
                throw new EntityNotFoundException("Book(id=" + id + ") is not found");
            }

            return collectBook(bookReadEntities);
    }

    @Override
    public List<Book> findAll() {
        return jdbcOperations.query(SELECT_BOOK_SQL, new BookReadEntityRowMapper())
                .stream()
                .collect(Collectors.groupingBy(BookReadEntity::bookId))
                .values()
                .stream()
                .map(this::collectBook)
                .toList();
    }

    @Override
    public Book update(Book book) {
        var params = new HashMap<String, Object>();
        params.put("genreId", book.genre().id());
        params.put("title", book.title());
        params.put("id", book.id());

        var updateResult = jdbcOperations.update(
                "UPDATE book b SET b.title = :title, b.genre_id = :genreId WHERE b.id = :id",
                params
        );

        if (updateResult == 0) {
            throw new EntityNotFoundException("Book(id=" + book.id() + ") is not found");
        }

        deleteAuthorRelations(book);
        insertAuthors(book.authors(), book.id());

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

        deleteAuthorRelations(book);

        return book;
    }

    private Book collectBook(List<BookReadEntity> entities) {
        return new Book(
                entities.getFirst().bookId(),
                entities.getFirst().bookTitle(),
                entities.stream()
                        .map((entity) ->
                                new Author(
                                        entity.authorId(),
                                        entity.authorFirstName(),
                                        entity.authorLastName()
                                )
                        )
                        .toList(),
                new Genre(
                        entities.getFirst().genreId(),
                        entities.getFirst().genreName()
                )
        );
    }

    private void insertAuthors(List<Author> authors, Long bookId) {
        authors.forEach(author -> {
                    var bookToAuthorParams = new HashMap<String, Object>();
                    bookToAuthorParams.put("authorId", author.id());
                    bookToAuthorParams.put("bookId", bookId);

                    jdbcOperations.update(
                            "INSERT INTO book_to_author(book_id, author_id) VALUES (:bookId, :authorId)",
                            bookToAuthorParams
                    );
                }
        );
    }

    private void deleteAuthorRelations(Book book) {
        jdbcOperations.update(
                "DELETE FROM book_to_author bta WHERE bta.book_id = :bookId",
                Collections.singletonMap("bookId", book.id())
        );
    }

    private static class BookReadEntityRowMapper implements RowMapper<BookReadEntity> {

        @Override
        public BookReadEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new BookReadEntity(
                    rs.getLong("bookId"),
                    rs.getString("bookTitle"),
                    rs.getLong("genreId"),
                    rs.getString("genreName"),
                    rs.getLong("authorId"),
                    rs.getString("authorFirstName"),
                    rs.getString("authorLastName")
            );
        }
    }

    private record BookReadEntity(
            Long bookId,
            String bookTitle,
            Long genreId,
            String genreName,
            Long authorId,
            String authorFirstName,
            String authorLastName
    ) {
    }
}
