package otus.spring.exception;

public class FailedToReadQuestionsException extends RuntimeException {

    public FailedToReadQuestionsException(String message) {
        super(message);
    }

    public FailedToReadQuestionsException(String message, Throwable cause) {
        super(message, cause);
    }
}
