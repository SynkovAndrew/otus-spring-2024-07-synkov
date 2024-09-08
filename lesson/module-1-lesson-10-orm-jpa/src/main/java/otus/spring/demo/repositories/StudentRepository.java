package otus.spring.demo.repositories;

import otus.spring.demo.models.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> findAllWithAllInfo();
}
