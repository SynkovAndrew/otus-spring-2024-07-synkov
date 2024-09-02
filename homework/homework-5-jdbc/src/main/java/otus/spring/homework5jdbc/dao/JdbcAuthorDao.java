package otus.spring.homework5jdbc.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import otus.spring.homework5jdbc.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcAuthorDao implements AuthorDao {
    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public Author create(CreateAuthorContext context) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        var params = new HashMap<String, Object>();
        params.put("firstName", context.firstName());
        params.put("lastName", context.lastName());

        jdbcOperations.update(
                "INSERT INTO author(first_name, last_name) VALUES (:firstName, :lastName)",
                new MapSqlParameterSource(params),
                keyHolder
        );

        return new Author(
                keyHolder.getKeyAs(Long.class),
                context.firstName(),
                context.lastName()
        );
    }

    @Override
    public Optional<Author> findById(Long id) {
        return Optional.ofNullable(
                jdbcOperations.queryForObject(
                        "SELECT id, first_name, last_name FROM author WHERE id = :id",
                        Collections.singletonMap("id", id),
                        new AuthorRowMapper()
                )
        );
    }

    @Override
    public List<Author> findAll() {
        return jdbcOperations.query(
                "SELECT id, first_name, last_name FROM author",
                new AuthorRowMapper()
        );
    }

    @Override
    public Author update(Author author) {
        var params = new HashMap<String, Object>();
        params.put("firstName", author.firstName());
        params.put("lastName", author.lastName());
        params.put("id", author.id());

        var result = jdbcOperations.update(
                "UPDATE author SET first_name=:firstName, last_name=:lastName WHERE id = :id",
                params
        );

        if (result == 0) {
            throw new EntityNotFoundException("Author(id=" + author.id() + ") is not found");
        }

        return author;
    }

    @Override
    public Author delete(Author author) {
        var result = jdbcOperations.update(
                "DELETE FROM author WHERE id = :id",
                Collections.singletonMap("id", author.id())
        );

        if (result == 0) {
            throw new EntityNotFoundException("Author(id=" + author.id() + ") is not found");
        }
        return author;
    }

    private static class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Author(
                    rs.getLong("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name")
            );
        }
    }
}
