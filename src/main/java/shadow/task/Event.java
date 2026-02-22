package shadow.task;

import shadow.TimeHandler;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Event is a subclass of Task with a [E] marker.
 * It represents a task that starts and ends at a specific time.
 */
public class Event extends Task {
    /** The start time of the task */
    private final TimeHandler startTime;
    /** The end time of the task */
    private final TimeHandler endTime;

    /**
     * The constructor for Event Task object.
     * Takes in the description, starting time and ending time of the task.
     *
     * @param name Description of the task being created.
     * @param startTime
     * @param endTime
     */
    public Event(String name, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        super(name);
        this.startTime = new TimeHandler(startDate, startTime);;
        this.endTime = new TimeHandler(endDate, endTime);
    }

    @Override
    public String toFileFormat() {
        return "E," + getIsDone() + "," + this.name + "," + this.startTime.taskDate() + "," + this.startTime.taskTime()
                + "," + this.endTime.taskDate() + "," + this.endTime.taskTime();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime.toString() + " to: " + this.endTime.toString() + ")";
    }
}