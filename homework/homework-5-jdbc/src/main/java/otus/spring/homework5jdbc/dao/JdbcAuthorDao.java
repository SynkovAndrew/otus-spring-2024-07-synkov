package otus.spring.homework5jdbc.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import otus.spring.homework5jdbc.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcAuthorDao implements AuthorDao {
    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public void create(Author author) {
        var params = new HashMap<String, Object>();
        params.put("firstName", author.firstName());
        params.put("lastName", author.lastName());

        jdbcOperations.update("INSERT INTO author(first_name, last_name) VALUES (:firstName, :lastName)", params);
    }

    @Override
    public Author findById(Long id) {
        return jdbcOperations.queryForObject(
                "SELECT id, first_name, last_name FROM author WHERE id = :id",
                Collections.singletonMap("id", id),
                new AuthorRowMapper()
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
    public void update(Author author) {
        var params = new HashMap<String, Object>();
        params.put("firstName", author.firstName());
        params.put("lastName", author.lastName());
        params.put("id", author.id());

        jdbcOperations.update(
                "UPDATE author SET first_name=:firstName, last_name=:lastName WHERE id = :id",
                params
        );
    }

    @Override
    public void deleteById(Long id) {
        jdbcOperations.update("DELETE FROM author WHERE id = :id", Collections.singletonMap("id", id));
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
