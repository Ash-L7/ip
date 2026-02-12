/**
 * Deadline is a subclass of Task with a [D] marker.
 * It represents task with a due date.
 */
public class Deadline extends Task{
    /** Due day of the task */
    private final String deadline;

    /**
     * The constructor for Deadline Task object.
     * Takes in the description and the due day of the task.
     *
     * @param name Description of the task being created.
     * @param deadline A string of a day in a week representing the due date of the task.
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
