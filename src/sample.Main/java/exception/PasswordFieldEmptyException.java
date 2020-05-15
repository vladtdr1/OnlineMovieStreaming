package exception;

public class PasswordFieldEmptyException extends Exception {
    public PasswordFieldEmptyException(){
        super("The password field is empty!");
    }
}