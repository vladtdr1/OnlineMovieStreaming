package exception;

public class UrlFieldEmptyException extends Exception {
    public UrlFieldEmptyException() {
        super("The url field is empty!");
    }
}
