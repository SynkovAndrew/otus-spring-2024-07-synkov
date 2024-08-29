package otus.spring.homework4springshell.service;


import otus.spring.homework4springshell.domain.Student;

import java.util.Optional;

public interface StudentHolder {

    Optional<Student> get();

    Boolean exists();

    void set(Student student);
}
