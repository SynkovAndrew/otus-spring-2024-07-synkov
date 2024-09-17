package otus.spring.homework6orm.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import otus.spring.homework6orm.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaGenreDao implements GenreDao {
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
  /*      try {
            return jdbcOperations.queryForObject(
                    SELECT_GENRE_SQL + " WHERE id = :id",
                    Collections.singletonMap("id", id),
                    new GenreRowMapper()
            );
        } catch (EmptyResultDataAccessException exception) {
            throw new DaoException.EntityNotFound("Genre(id=" + id + ") is not found");
        }*/
        return null;
    }

    @Override
    public List<Genre> findAll() {
       // return jdbcOperations.query(SELECT_GENRE_SQL, new GenreRowMapper());
        return Collections.emptyList();
    }

    @Override
    public Genre update(Genre genre) {
/*        var params = new HashMap<String, Object>();
        params.put("name", genre.name());
        params.put("id", genre.id());

        var result = jdbcOperations.update(
                "UPDATE genre SET name = :name WHERE id = :id",
                params
        );

        if (result == 0) {
            throw new DaoException.EntityNotFound("Genre(id=" + genre.id() + ") is not found");
        }*/

        return genre;
    }

    @Override
    public Genre delete(Genre genre) {
        var result = jdbcOperations.update(
                "DELETE FROM genre WHERE id = :id",
                Collections.singletonMap("id", genre.getId())
        );

        if (result == 0) {
            throw new DaoException.EntityNotFound("Genre(id=" + genre.getId() + ") is not found");
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
