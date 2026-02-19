/**
 * The representation of a task,
 * Records the description and completion status of the task.
 */
public class Task {
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
        this.name = name;
        this.isDone = false;
    }

    /**
     * Checks isDone field and returns whether the task is completed.
     *
     * @return A String representation of whether a task is completed.
     */
    public String getIsDone() {
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
            System.out.println("- Nice! I've marked this task as done: ");
            System.out.println(this.toString());
        } else {
            this.isDone = false;
            System.out.println("- OK, I've marked this task as not done yet: ");
            System.out.println(this.toString());
        }
    }

    public String toFileFormat() {
        return "";
    }

    @Override
    public String toString() {
        return "[" + getIsDone() + "] " + this.name;
    }
}
