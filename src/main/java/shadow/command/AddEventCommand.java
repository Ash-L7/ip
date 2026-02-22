package shadow.command;

import shadow.TaskList;
import shadow.TimeHandler;
import shadow.task.Event;
import shadow.ui.Ui;

/**
 * Represents a command to add an event task to the task list.
 * This command handles the creation of an Event object and updates the UI accordingly.
 */
public class AddEventCommand extends Command {
    private final String taskDescription;
    private final TimeHandler start;
    private final TimeHandler end;

    /**
     * Initializes the command with the task description and its start and end times.
     *
     * @param taskDescription
     * @param start Start time of the event
     * @param end End time of the event
     */
    public AddEventCommand(String taskDescription, TimeHandler start, TimeHandler end) {
        this.taskDescription = taskDescription;
        this.start = start;
        this.end = end;
    }

    /**
     * Adds an event to the task list using the stored description and time parameters.
     *
     * @param taskList The task list object referred to by the command.
     * @param ui User interface handling interaction with user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        Event event = new Event(taskDescription, start.taskDate(), start.taskTime(),
                end.taskDate(), end.taskTime());
        taskList.addTask(event);
        ui.showTaskAdded(event, taskList.getSize());
    }
}
