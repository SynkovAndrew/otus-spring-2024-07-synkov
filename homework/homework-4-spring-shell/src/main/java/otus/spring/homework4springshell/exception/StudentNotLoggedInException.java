package otus.spring.homework4springshell.exception;

public class StudentNotLoggedInException extends RuntimeException {

    public StudentNotLoggedInException(String message) {
        super(message);
    }

    public StudentNotLoggedInException(String message, Throwable cause) {
        super(message, cause);
    }
}
