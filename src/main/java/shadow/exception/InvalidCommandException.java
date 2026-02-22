package shadow.exception;

public class InvalidCommandException extends ShadowException {
    /**
     * Creates a new shadow.exception.ShadowException with the specified error message when a non-existing command is
     * entered
     *
     * @param message The error message describing what went wrong
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
