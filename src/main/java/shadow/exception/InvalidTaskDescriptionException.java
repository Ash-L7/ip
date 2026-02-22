package shadow.exception;

public class InvalidTaskDescriptionException extends ShadowException {
    /**
     * Creates a new shadow.exception.ShadowException with the specified error message when no task description is
     * given.
     *
     * @param message The error message describing what went wrong
     */
    public InvalidTaskDescriptionException(String message) {
        super(message);
    }
}
