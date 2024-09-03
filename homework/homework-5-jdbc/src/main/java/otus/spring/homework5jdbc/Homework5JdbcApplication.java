package otus.spring.homework5jdbc;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class Homework5JdbcApplication {

    public static void main(String[] args) throws SQLException {
        Console.main(args);

        SpringApplication.run(Homework5JdbcApplication.class, args);
    }

}
