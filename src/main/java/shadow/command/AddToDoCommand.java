package shadow.command;

import shadow.TaskList;
import shadow.task.ToDo;
import shadow.ui.Ui;

public class AddToDoCommand extends Command {
    private final String taskDescription;

    public AddToDoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ToDo toDo = new ToDo(taskDescription);
        taskList.addTask(toDo);
        ui.showTaskAdded(toDo, taskList.getSize());
    }
}
