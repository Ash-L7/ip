package shadow.command;

import shadow.TaskList;
import shadow.task.Deadline;
import shadow.TimeHandler;
import shadow.ui.Ui;

public class AddDeadlineCommand extends Command {
    private final String taskDescription;
    private final TimeHandler due;

    public AddDeadlineCommand(String taskDescription, TimeHandler deadline) {
        this.taskDescription = taskDescription;
        this.due = deadline;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Deadline deadline = new Deadline(taskDescription, due.taskDate(),
                due.taskTime());
        taskList.addTask(deadline);
        ui.showTaskAdded(deadline, taskList.getSize());
    }
}
