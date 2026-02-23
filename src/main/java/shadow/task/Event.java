package shadow.task;

import java.time.LocalDate;
import java.time.LocalTime;

import shadow.TimeHandler;

/**
 * Represents a task that occurs during a specific time period.
 * Extends the Task class with start and end time functionality.
 */
public class Event extends Task {
    /**
     * The start date and time of the event.
     */
    private final TimeHandler startTime;
    /**
     * The end date and time of the event.
     */
    private final TimeHandler endTime;

    /**
     * Constructs an Event task with the given description and time range.
     *
     * @param name      The description of the task.
     * @param startDate The start date of the event.
     * @param startTime The start time of the event.
     * @param endDate   The end date of the event.
     * @param endTime   The end time of the event.
     */
    public Event(String name, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        super(name);
        this.startTime = new TimeHandler(startDate, startTime);
        this.endTime = new TimeHandler(endDate, endTime);
    }

    /**
     * Returns the start LocalDate of the event.
     *
     * @return start date
     */
    public LocalDate getStartDate() {
        return this.startTime.taskDate();
    }

    /**
     * Returns the start LocalTime of the event.
     *
     * @return start time
     */
    public LocalTime getStartTime() {
        return this.startTime.taskTime();
    }

    @Override
    public String toFileFormat() {
        return "E," + this.getIsDone() + "," + this.name + "," + this.startTime.taskDate() + "," + this.startTime.taskTime()
                + "," + this.endTime.taskDate() + "," + this.endTime.taskTime();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime.toString() + " to: " + this.endTime.toString() + ")";
    }
}