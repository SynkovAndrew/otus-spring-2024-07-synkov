package otus.spring.homework6orm;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DatabasePreparer {
    private final NamedParameterJdbcOperations jdbcOperations;

    public DatabasePreparer(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public void clearBooks() {
        jdbcOperations.update("DELETE FROM book_to_author", Collections.emptyMap());
        jdbcOperations.update("DELETE FROM book", Collections.emptyMap());
    }

    public void clearAuthors() {
        jdbcOperations.update("DELETE FROM author", Collections.emptyMap());
    }

    public void clearGenres() {
        jdbcOperations.update("DELETE FROM genre", Collections.emptyMap());
    }
}
