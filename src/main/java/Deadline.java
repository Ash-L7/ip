import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Deadline is a subclass of Task with a [D] marker.
 * It represents task with a due date.
 */
public class Deadline extends Task{
    /** Due day of the task */
    private final TimeHandler deadline;

    /**
     * The constructor for Deadline Task object.
     * Takes in the description and the due day of the task.
     *
     * @param name Description of the task being created.
     * @param deadlineDate
     * @param deadlineTime
     */
    public Deadline(String name, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(name);
        this.deadline = new TimeHandler(deadlineDate, deadlineTime);
    }

    public String toFileFormat() {
        return "D," + getIsDone() + "," + this.name + "," + this.deadline.taskDate() + "," + this.deadline.taskTime();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.toString() + ")";
    }
}
