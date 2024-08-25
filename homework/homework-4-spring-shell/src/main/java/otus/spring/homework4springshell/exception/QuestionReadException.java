package otus.spring.homework4springshell.exception;

public class QuestionReadException extends RuntimeException {

    public QuestionReadException(String message) {
        super(message);
    }

    public QuestionReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
