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
import otus.spring.homework5jdbc.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcGenreDao implements GenreDao {
    private static final String SELECT_GENRE_SQL = "SELECT id, name FROM genre";

    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public Genre create(CreateGenreContext context) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        var params = new HashMap<String, Object>();
        params.put("name", context.name());

        jdbcOperations.update(
                "INSERT INTO genre(name) VALUES (:name)",
                new MapSqlParameterSource(params),
                keyHolder
        );

        return new Genre(keyHolder.getKeyAs(Long.class), context.name());
    }

    @Override
    public Genre findById(Long id) {
        try {
            return jdbcOperations.queryForObject(
                    SELECT_GENRE_SQL + " WHERE id = :id",
                    Collections.singletonMap("id", id),
                    new GenreRowMapper()
            );
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException("Genre(id=" + id + ") is not found");
        }
    }

    @Override
    public List<Genre> findAll() {
        return jdbcOperations.query(SELECT_GENRE_SQL, new GenreRowMapper());
    }

    @Override
    public Genre update(Genre genre) {
        var params = new HashMap<String, Object>();
        params.put("name", genre.name());
        params.put("id", genre.id());

        var result = jdbcOperations.update(
                "UPDATE genre SET name = :name WHERE id = :id",
                params
        );

        if (result == 0) {
            throw new EntityNotFoundException("Genre(id=" + genre.id() + ") is not found");
        }

        return genre;
    }

    @Override
    public Genre delete(Genre genre) {
        var result = jdbcOperations.update(
                "DELETE FROM genre WHERE id = :id",
                Collections.singletonMap("id", genre.id())
        );

        if (result == 0) {
            throw new EntityNotFoundException("Genre(id=" + genre.id() + ") is not found");
        }
        return genre;
    }

    private static class GenreRowMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Genre(
                    rs.getLong("id"),
                    rs.getString("name")
            );
        }
    }
}
