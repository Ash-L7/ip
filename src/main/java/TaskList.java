import java.util.ArrayList;

/**
 *
 */
public class TaskList {
    /** List recording all the task added by user */
    private ArrayList<Task> taskLists;
    /** Number of tasks in the list */
    private int numberOfTasks;

    public TaskList() {
        this.taskLists = new ArrayList<Task>();
        this.numberOfTasks = 0;
    }

    /**
     * Adds task to the list of tasks.
     * Prints out the task description of the task added and the total number of task currently in the list.
     *
     * @param task
     */
    public void addTask(Task task) {
        this.taskLists.add(task);
        System.out.println("added: " + task.toString());
        System.out.println("Now you have " + (this.numberOfTasks + 1) + " tasks in the list.");
        numberOfTasks++;
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
        String taskRemoved = taskLists.get(index - 1).toString();
        taskLists.remove(index - 1);
        numberOfTasks--;

        System.out.println("Noted. I've removed this task:\n" + taskRemoved + "\nNow you have " + numberOfTasks +
                " tasks in the list.");
    }

    /**
     * Returns the task at the specified position in the list.
     *
     * @param index Position of the task in the list.
     * @return Task object at the specified position in the list.
     */
    public Task getTask(int index) {
        return this.taskLists.get(index - 1);
    }
}
