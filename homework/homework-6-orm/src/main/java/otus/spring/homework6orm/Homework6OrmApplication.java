package otus.spring.homework6orm;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class Homework6OrmApplication {

    public static void main(String[] args) throws SQLException {
        Console.main(args);

        SpringApplication.run(Homework6OrmApplication.class, args);
    }
}
