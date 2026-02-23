package shadow.task;

import java.util.ArrayList;

import shadow.ui.Ui;

/**
 * The shadow.task.TaskList class maintains a collection of shadow.task.Task objects using an ArrayList.
 */
public class TaskList {
    /** List recording all the task added by user */
    private final ArrayList<Task> taskLists;
    /** Number of tasks in the list */
    private int numberOfTasks;

    /**
     * The constructor for a shadow.task.TaskList. It initializes the task list.
     */
    public TaskList() {
        this.taskLists = new ArrayList<>();
        this.numberOfTasks = 0;
    }

    public int getSize() {
        assert this.numberOfTasks >= 0 : "numberOfTasks cannot be negative";
        assert this.numberOfTasks == this.taskLists.size() : "numberOfTasks must match ArrayList size";
        return this.numberOfTasks;
    }

    /**
     * Adds task to the list of tasks.
     * Prints out the task description of the task added and the total number of task currently in the list.
     *
     * @param task shadow.task.Task object to be added to the list.
     */
    public void addTask(Task task) {
        assert task != null : "Cannot add a null task to the list";
        this.taskLists.add(task);
        numberOfTasks++;
        assert this.numberOfTasks == this.taskLists.size() : "After adding, numberOfTasks must match ArrayList size";
    }

    /**
     * Displays all the task and their completion status currently in the list.
     */
    public void outputTaskList() {
        for (int i = 1; i <= numberOfTasks; i++) {
            System.out.println(i + ". " + taskLists.get(i - 1).toString());
        }
    }

    /**
     * Removes the task in the specified position from the task list.
     *
     * @param index Position of the task in the list
     */
    public void removeTask(int index) {
        assert index >= 1 && index <= this.numberOfTasks : "Task index must be between 1 and numberOfTasks";
        String taskRemoved = taskLists.get(index - 1).toString();
        taskLists.remove(index - 1);
        numberOfTasks--;
        assert this.numberOfTasks == this.taskLists.size() : "After removal, numberOfTasks must match ArrayList size";

        System.out.println("- Noted. I've removed this task:\n" + taskRemoved + "\nNow you have " + numberOfTasks +
                " tasks in the list.");
    }

    /**
     * Returns the task at the specified position in the list.
     *
     * @param index Position of the task in the list.
     * @return shadow.task.Task object at the specified position in the list.
     */
    public Task getTask(int index) {
        assert index >= 1 && index <= this.numberOfTasks : "Task index must be between 1 and numberOfTasks (inclusive)";
        return this.taskLists.get(index - 1);
    }

    public void findTask(String taskDescription, Ui ui) {
        boolean hasMatch = false;
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : this.taskLists) {
            if (task.toString().contains(taskDescription)) {
                hasMatch = true;
                matchingTasks.add(task);
            }
        }

        if (!hasMatch) {
            ui.showSearchFail();
        } else {
            ui.showSearchSuccessful();

            for (int j = 0; j < matchingTasks.size(); j++) {
                System.out.print((j + 1) + ". ");
                ui.showTask(matchingTasks.get(j));
            }
        }
    }
}
