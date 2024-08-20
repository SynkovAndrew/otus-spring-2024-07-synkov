package otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import otus.spring.domain.Student;

@Component
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final IOService ioService;

    @Override
    public Student readCurrentStudent() {
        ioService.printFormattedLine("First Name: ");
        var firstName = ioService.readLine();

        ioService.printFormattedLine("Last Name: ");
        var lastName = ioService.readLine();

        return new Student(firstName, lastName);
    }
}
