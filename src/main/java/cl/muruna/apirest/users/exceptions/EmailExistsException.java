package cl.muruna.apirest.users.exceptions;

public class EmailExistsException extends RuntimeException{
    public EmailExistsException(String message) {
        super(message);
    }
}
