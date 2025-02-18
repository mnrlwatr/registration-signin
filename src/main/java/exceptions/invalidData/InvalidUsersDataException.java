package exceptions.invalidData;

public abstract class InvalidUsersDataException extends Exception {
    protected final String message;

    protected InvalidUsersDataException(String message) {
        this.message = message;
    }
}
