package shadow.task;

/**
 * Represents an abstract task with a description and completion status.
 * Subclasses should implement task-specific formatting for file storage.
 */
public abstract class Task {
    /** The description of the task. */
    protected final String name;
    /** Tracks whether the task has been completed. */
    private boolean isDone;

    /**
     * Constructs a Task with the given description.
     * The task is initialized as incomplete.
     *
     * @param name The description of the task.
     */
    public Task(String name) {
        assert name != null : "Task name cannot be null";
        assert !name.trim().isEmpty() : "Task name cannot be empty";
        this.name = name;
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task's completion status.
     *
     * @return "X" if task is done, " " (space) if not done.
     */
    public String getIsDone() {
        assert this.isDone == true || this.isDone == false : "isDone must be a valid boolean value";
        if (this.isDone) {
            return "X";
        }

        return " ";
    }

    /**
     * Sets the completion status of the task.
     * Note: This method only updates the status without producing output.
     * Output formatting should be handled by the caller.
     *
     * @param status true to mark task as done, false to mark as not done.
     */
    public void setDone(boolean status) {
        if (status) {
            this.isDone = true;
            assert this.isDone : "isDone should be true after setDone(true)";
            System.out.println("- Nice! I've marked this task as done: ");
            System.out.println(this.toString());
        } else {
            this.isDone = false;
            assert !this.isDone : "isDone should be false after setDone(false)";
            System.out.println("- OK, I've marked this task as not done yet: ");
            System.out.println(this.toString());
        }
    }

    /**
     * Converts the task to a string representation for file storage.
     * Subclasses must implement this method.
     *
     * @return A string representing how the task should be recorded in the save file.
     */
    public abstract String toFileFormat();

    @Override
    public String toString() {
        return "[" + isDone() + "] " + this.name;
    }
}