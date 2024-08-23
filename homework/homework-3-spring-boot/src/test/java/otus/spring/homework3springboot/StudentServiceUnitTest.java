package otus.spring.homework3springboot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import otus.spring.homework3springboot.domain.Student;
import otus.spring.homework3springboot.service.LocalizedIOService;
import otus.spring.homework3springboot.service.StudentService;
import otus.spring.homework3springboot.service.StudentServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceUnitTest {
    @Mock
    private LocalizedIOService ioService;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    public void readCurrentStudentTest() {
        when(ioService.readLine()).thenReturn("Test");
        doNothing().when(ioService).printLocalizedFormattedLine(any());

        assertEquals(studentService.readCurrentStudent(), new Student("Test", "Test"));
    }
}
