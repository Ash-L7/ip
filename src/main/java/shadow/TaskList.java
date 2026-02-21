package shadow;

import shadow.task.Task;

import java.util.ArrayList;

/**
 * The shadow.TaskList class maintains a collection of shadow.task.Task objects using an ArrayList.
 */
public class TaskList {
    /** List recording all the task added by user */
    private final ArrayList<Task> taskLists;
    /** Number of tasks in the list */
    private int numberOfTasks;

    /**
     * The constructor for a shadow.TaskList. It initializes the task list.
     */
    public TaskList() {
        this.taskLists = new ArrayList<>();
        this.numberOfTasks = 0;
    }

    public int getSize() {
        return this.numberOfTasks;
    }

    /**
     * Adds task to the list of tasks.
     * Prints out the task description of the task added and the total number of task currently in the list.
     *
     * @param task shadow.task.Task object to be added to the list.
     */
    public void addTask(Task task) {
        this.taskLists.add(task);
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
        return this.taskLists.get(index - 1);
    }
}
