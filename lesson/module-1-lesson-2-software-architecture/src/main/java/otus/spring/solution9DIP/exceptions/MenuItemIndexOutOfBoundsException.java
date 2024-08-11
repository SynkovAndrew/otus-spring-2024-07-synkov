package otus.spring.solution9DIP.exceptions;

public class MenuItemIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public MenuItemIndexOutOfBoundsException(String s) {
        super(s);
    }
}
