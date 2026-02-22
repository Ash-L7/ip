package shadow.command;

import shadow.TaskList;
import shadow.ui.Ui;

public class FindCommand extends Command {
    private final String taskToFind;

    public FindCommand(String taskDescription) {
        this.taskToFind = taskDescription;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.showSearchProcessing();
        taskList.findTask(taskToFind, ui);
    }
}
