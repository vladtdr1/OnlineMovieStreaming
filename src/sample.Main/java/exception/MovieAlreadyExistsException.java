package exception;

public class MovieAlreadyExistsException extends Exception {
    public MovieAlreadyExistsException() {
        super("The movie already exists!");
    }
}
