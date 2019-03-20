package liam.dea.Exceptions;

public class DatabaseItemNotFoundException extends RuntimeException {
    public DatabaseItemNotFoundException(String message){
        super(message);
    }
}
