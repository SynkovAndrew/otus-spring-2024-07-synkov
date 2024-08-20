package otus.spring.homework3springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import otus.spring.homework3springboot.domain.Student;

@Component
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final LocalizedIOService ioService;

    @Override
    public Student readCurrentStudent() {
        ioService.printLocalizedFormattedLine("first.name.title");
        var firstName = ioService.readLine();

        ioService.printFormattedLine("last.name.title");
        var lastName = ioService.readLine();

        return new Student(firstName, lastName);
    }
}
