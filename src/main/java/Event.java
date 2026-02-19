/**
 * Event is a subclass of Task with a [E] marker.
 * It represents a task that starts and ends at a specific time.
 */
public class Event extends Task {
    /** The start time of the task */
    private final String startTime;
    /** The end time of the task */
    private final String endTime;
    private String name;

    /**
     * The constructor for Event Task object.
     * Takes in the description, starting time and ending time of the task.
     *
     * @param name Description of the task being created.
     * @param startTime
     * @param endTime
     */
    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String toFileFormat() {
        return "T," + getIsDone() + "," + this.name + "," + this.startTime + "," + this.endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
