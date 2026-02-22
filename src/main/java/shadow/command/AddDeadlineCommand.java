package shadow.command;

import shadow.TaskList;
import shadow.task.Deadline;
import shadow.TimeHandler;
import shadow.ui.Ui;

/**
 * Represents a command to add a deadline task to the task list.
 * This command handles the creation of a Deadline object and updates the UI accordingly.
 */
public class AddDeadlineCommand extends Command {
    private final String taskDescription;
    private final TimeHandler due;

    /**
     * Initializes the command with the task description and its due date.
     *
     * @param taskDescription
     * @param deadline Due date and time of the task
     */
    public AddDeadlineCommand(String taskDescription, TimeHandler deadline) {
        this.taskDescription = taskDescription;
        this.due = deadline;
    }

    /**
     * Adds deadline task to the task list using the stored description and time parameters.
     *
     * @param taskList The task list object referred to by the command.
     * @param ui User interface handling interaction with user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        Deadline deadline = new Deadline(taskDescription, due.taskDate(),
                due.taskTime());
        taskList.addTask(deadline);
        ui.showTaskAdded(deadline, taskList.getSize());
    }
}
