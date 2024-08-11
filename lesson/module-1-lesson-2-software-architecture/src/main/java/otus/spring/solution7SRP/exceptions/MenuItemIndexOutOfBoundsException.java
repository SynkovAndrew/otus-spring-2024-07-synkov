package otus.spring.solution7SRP.exceptions;

public class MenuItemIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public MenuItemIndexOutOfBoundsException(String s) {
        super(s);
    }
}