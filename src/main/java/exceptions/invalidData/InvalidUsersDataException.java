package exceptions.invalidData;

public abstract class InvalidUsersDataException extends Exception {
    protected InvalidUsersDataException(String message) {
        super(message);
    }
}
