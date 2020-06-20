package exception;

public class IncorrectPasswordException extends Exception {
    public IncorrectPasswordException() {
        super("Incorrect Password");
    }
}