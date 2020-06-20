package exception;

public class TitleFieldEmptyException extends Exception {
    public TitleFieldEmptyException() {
        super("The title field is empty!");
    }
}
