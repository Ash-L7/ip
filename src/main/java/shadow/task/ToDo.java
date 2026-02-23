package shadow.task;

/**
 * Represents a basic task without any time constraints.
 * Extends the Task class with minimal functionality.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task with the given description.
     *
     * @param name The description of the task.
     */
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toFileFormat() {
        return "T," + isDone() + "," + this.name;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}