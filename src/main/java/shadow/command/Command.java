package shadow.command;

import shadow.ui.Ui;
import shadow.TaskList;

public abstract class Command {
    /**
     * Executes the command given by user.
     *
     * @param taskList The task list object referred to by the command.
     * @param ui User interface handling interaction with user.
     */
    public abstract void execute(TaskList taskList, Ui ui);
}
