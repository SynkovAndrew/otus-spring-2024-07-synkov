package otus.spring.exersice_1_solution.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import otus.spring.exersice_1_solution.domain.Person;

import javax.swing.text.html.Option;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PersonDaoJdbc implements PersonDao {
    private final JdbcOperations jdbcOperations;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public int count() {
        /*return Optional
                .ofNullable(
                        jdbcOperations.queryForObject(
                                "select count(*) from persons",
                                Integer.class
                        )
                )
                .orElse(0);*/
        return Optional
                .ofNullable(
                        namedParameterJdbcOperations.queryForObject(
                                "select count(*) from persons",
                                Collections.emptyMap(),
                                Integer.class
                        )
                )
                .orElse(0);
    }

    @Override
    public void insert(Person person) {
        /*jdbcOperations.update(
                "insert into persons(id, name) values (?, ?)",
                person.getId(),
                person.getName()
        );*/

        var params = new HashMap<String, Object>();
        params.put("name", person.getName());
        params.put("id", person.getId());


        namedParameterJdbcOperations.update("insert into persons(id, name) values (:id, :name)", params);
    }

    @Override
    public Person getById(long id) {
//        return jdbcOperations.queryForObject(
//                "select * from persons where id = ?",
//                new PersonRowMapper(),
//                id
//        );

        return namedParameterJdbcOperations.queryForObject(
                "select * from persons where id = :id",
                Collections.singletonMap("id", id),
                new PersonRowMapper()
        );
    }

    @Override
    public List<Person> getAll() {
        //return jdbcOperations.query("select * from persons", new PersonRowMapper());
        return namedParameterJdbcOperations.query("select * from persons", new PersonRowMapper());
    }

    @Override
    public void deleteById(long id) {
       // jdbcOperations.update("delete from persons where id = ?", id);

        namedParameterJdbcOperations.update(
                "delete from persons where id = :id",
                Collections.singletonMap("id", id)
        );
    }

    private static class PersonRowMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Person(
                    rs.getInt("id"),
                    rs.getString("name")
            );
        }
    }
}
