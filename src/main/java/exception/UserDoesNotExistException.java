package exception;

public class UserDoesNotExistException extends Exception {
    public UserDoesNotExistException() {
        super("User does not exist");
    }
}