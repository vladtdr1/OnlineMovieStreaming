package exception;

public class UsernameFieldEmptyException extends Exception {
    public UsernameFieldEmptyException() {
        super("The username field is empty!");
    }
}
