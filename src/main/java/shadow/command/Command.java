package shadow.command;

import shadow.ui.Ui;
import shadow.TaskList;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui);
}
