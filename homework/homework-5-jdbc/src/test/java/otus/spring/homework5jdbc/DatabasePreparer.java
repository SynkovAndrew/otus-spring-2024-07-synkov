package otus.spring.homework5jdbc;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DatabasePreparer {
    private final NamedParameterJdbcOperations jdbcOperations;

    public DatabasePreparer(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public void clearAuthors() {
        jdbcOperations.update("DELETE FROM author", Collections.emptyMap());
    }
}
