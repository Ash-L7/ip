public class InvalidCommandException extends ShadowException {
    /**
     * Creates a new ShadowException with the specified error message
     *
     * @param message The error message describing what went wrong
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
