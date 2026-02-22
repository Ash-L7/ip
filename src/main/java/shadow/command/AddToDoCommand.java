package shadow.command;

import shadow.TaskList;
import shadow.task.ToDo;
import shadow.ui.Ui;

/**
 * Represents a command to add a to do task to the task list.
 * This command handles the creation of an ToDo object and updates the UI accordingly.
 */
public class AddToDoCommand extends Command {
    private final String taskDescription;

    /**
     * Initializes the command with the task description and its start and end times.
     *
     * @param taskDescription
     */
    public AddToDoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Adds a toDo task to the task list using the stored description.
     *
     * @param taskList The task list object referred to by the command.
     * @param ui User interface handling interaction with user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ToDo toDo = new ToDo(taskDescription);
        taskList.addTask(toDo);
        ui.showTaskAdded(toDo, taskList.getSize());
    }
}
