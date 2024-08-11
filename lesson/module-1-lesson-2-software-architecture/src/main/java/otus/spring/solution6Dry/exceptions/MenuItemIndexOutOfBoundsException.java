package otus.spring.solution6Dry.exceptions;

public class MenuItemIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public MenuItemIndexOutOfBoundsException(String s) {
        super(s);
    }
}