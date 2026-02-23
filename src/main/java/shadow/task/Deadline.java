package shadow.task;

import java.time.LocalDate;
import java.time.LocalTime;

import shadow.TimeHandler;

/**
 * Represents a task with a deadline (due date and time).
 * Extends the Task class with deadline-specific functionality.
 */
public class Deadline extends Task {
    /**
     * The due date and time for this deadline.
     */
    private final TimeHandler deadline;

    /**
     * Constructs a Deadline task with the given description and due date/time.
     *
     * @param name         The description of the task.
     * @param deadlineDate The due date for the deadline.
     * @param deadlineTime The due time for the deadline.
     */
    public Deadline(String name, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(name);
        this.deadline = new TimeHandler(deadlineDate, deadlineTime);
    }

    /**
     * Returns the LocalDate of this deadline.
     *
     * @return the due date
     */
    public LocalDate getDeadlineDate() {
        return this.deadline.taskDate();
    }

    /**
     * Returns the LocalTime of this deadline.
     *
     * @return the due time
     */
    public LocalTime getDeadlineTime() {
        return this.deadline.taskTime();
    }

    @Override
    public String toFileFormat() {
        return "D," + this.getIsDone() + "," + this.name + "," + this.deadline.taskDate() + "," + this.deadline.taskTime();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.toString() + ")";
    }
}