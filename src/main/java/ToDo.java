/**
 * ToDo is a subclass of Task with a [T] marker.
 * It represents the most basic kind of task
 * with no additional date or time fields
 * and only records the description of the task
 */
public class ToDo extends Task {
    private String name;

    /**
     * The constructor for ToDo Task object.
     * Initializes isDone and marks task as incomplete by default.
     *
     * @param name Description of the task being created.
     */
    public ToDo(String name) {
        super(name);
    }

    public String toFileFormat() {
        return "T," + getIsDone() + "," + this.name;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
