package otus.spring.demo.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import otus.spring.demo.repositories.CourseRepositoryJdbc;
import otus.spring.demo.repositories.StudentRepositoryJdbc;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jdbc для работы со студентами ")
@JdbcTest
@Import({StudentRepositoryJdbc.class, CourseRepositoryJdbc.class})
class StudentRepositoryJdbcTest {

    private static final int EXPECTED_NUMBER_OF_STUDENTS = 10;

    @Autowired
    private StudentRepositoryJdbc repositoryJdbc;

    @DisplayName("должен загружать список всех студентов с полной информацией о них")
    @Test
    void shouldReturnCorrectStudentsListWithAllInfo() {
        var students = repositoryJdbc.findAllWithAllInfo();
        assertThat(students).isNotNull().hasSize(EXPECTED_NUMBER_OF_STUDENTS)
                .allMatch(s -> !s.getName().isEmpty())
                .allMatch(s -> s.getCourses() != null && !s.getCourses().isEmpty())
                .allMatch(s -> s.getAvatar() != null)
                .allMatch(s -> s.getEmails() != null && !s.getEmails().isEmpty());
        students.forEach(System.out::println);
    }
}