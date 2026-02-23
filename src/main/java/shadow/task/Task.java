package shadow.task;

/**
 * The representation of a task,
 * Records the description and completion status of the task.
 */
public abstract class Task {
    /** The description of the task. */
    final String name;
    /** Keeps track of whether task has been completed. */
    private boolean isDone;

    /**
     * The constructor for Task object.
     * Initializes isDone and marks task as incomplete by default.
     *
     * @param name Description of the task being created.
     */
    public Task(String name) {
        assert name != null : "Task name cannot be null";
        assert !name.trim().isEmpty() : "Task name cannot be empty";
        this.name = name;
        this.isDone = false;
    }

    /**
     * Checks isDone field and returns whether the task is completed.
     *
     * @return A String representation of whether a task is completed.
     */
    public String getIsDone() {
        assert this.isDone == true || this.isDone == false : "isDone must be a valid boolean value";
        if (this.isDone) {
            return "X";
        }

        return " ";
    }

    /**
     * Marks a task as complete or incomplete base on user input.
     * Displays a string representation of the task being marked
     * and a string prompt to tell user whether the task is being marked as complete or incomplete.
     *
     * @param status Marks task as complete or incomplete.
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
     * Converts task into a string suitable for storing in a .csv file
     *
     * @return A string representing the way the task should be recorded in the save file.
     */
    public abstract String toFileFormat();

    @Override
    public String toString() {
        return "[" + getIsDone() + "] " + this.name;
    }
}