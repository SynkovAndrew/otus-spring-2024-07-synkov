package otus.spring.homework3springboot;

import org.junit.jupiter.api.Test;
import otus.spring.homework3springboot.domain.Student;
import otus.spring.homework3springboot.service.IOService;
import otus.spring.homework3springboot.service.StudentService;
import otus.spring.homework3springboot.service.StudentServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentServiceUnitTest {
    private final IOService ioService = mock(IOService.class);

    private final StudentService studentService = new StudentServiceImpl(ioService);

    @Test
    void readCurrentStudentTest() {
        when(ioService.readLine()).thenReturn("Test");
        doNothing().when(ioService).printFormattedLine(any());

        assertEquals(studentService.readCurrentStudent(), new Student("Test", "Test"));
    }
}
