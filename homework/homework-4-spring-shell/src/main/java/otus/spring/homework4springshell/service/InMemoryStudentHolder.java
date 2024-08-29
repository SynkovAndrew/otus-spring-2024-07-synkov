package otus.spring.homework4springshell.service;

import org.springframework.stereotype.Component;
import otus.spring.homework4springshell.domain.Student;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class InMemoryStudentHolder implements StudentHolder {
    private final AtomicReference<Student> studentSlot = new AtomicReference<>();

    @Override
    public Optional<Student> get() {
        return Optional.ofNullable(studentSlot.get());
    }

    @Override
    public Boolean exists() {
        return studentSlot.get() != null;
    }

    @Override
    public void set(Student student) {
        studentSlot.set(student);
    }
}
