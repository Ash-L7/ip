package shadow.command;

import shadow.TaskList;
import shadow.TimeHandler;
import shadow.task.Event;
import shadow.ui.Ui;

public class AddEventCommand extends Command {
    private final String taskDescription;
    private final TimeHandler start;
    private final TimeHandler end;

    public AddEventCommand(String taskDescription, TimeHandler start, TimeHandler end) {
        this.taskDescription = taskDescription;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Event event = new Event(taskDescription, start.taskDate(), start.taskTime(),
                end.taskDate(), end.taskTime());
        taskList.addTask(event);
        ui.showTaskAdded(event, taskList.getSize());
    }
}
