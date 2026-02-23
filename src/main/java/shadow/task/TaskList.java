package shadow.task;

import java.util.ArrayList;

/**
 * Manages a collection of Task objects.
 * Provides methods for adding, removing, and searching tasks.
 */
public class TaskList {
    /** List recording all the tasks added by the user. */
    private final ArrayList<Task> taskLists;
    /** Number of tasks currently in the list. */
    private int numberOfTasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.taskLists = new ArrayList<>();
        this.numberOfTasks = 0;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getSize() {
        assert this.numberOfTasks >= 0 : "numberOfTasks cannot be negative";
        assert this.numberOfTasks == this.taskLists.size() : "numberOfTasks must match ArrayList size";
        return this.numberOfTasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The Task object to add.
     */
    public void addTask(Task task) {
        assert task != null : "Cannot add a null task to the list";
        this.taskLists.add(task);
        numberOfTasks++;
        assert this.numberOfTasks == this.taskLists.size() : "After adding, numberOfTasks must match ArrayList size";
    }

    /**
     * Displays all tasks and their completion status currently in the list.
     */
    public void outputTaskList() {
        for (int i = 1; i <= numberOfTasks; i++) {
            System.out.println(i + ". " + taskLists.get(i - 1).toString());
        }
    }

    /**
     * Returns a formatted string of all tasks in the list.
     *
     * @return Formatted string of all tasks, or message if list is empty.
     */
    public String getFormattedTaskList() {
        if (numberOfTasks == 0) {
            return "- No tasks in the list.";
        }

        StringBuilder response = new StringBuilder();
        for (int i = 1; i <= numberOfTasks; i++) {
            response.append(i + ". " + taskLists.get(i - 1).toString());
            if (i < numberOfTasks) {
                response.append("\n");
            }
        }
        return response.toString();
    }

    /**
     * Removes the task at the specified position from the task list.
     *
     * @param index Position of the task in the list (1-based).
     * @return The String representation of the removed task.
     */
    public String removeTask(int index) {
        assert index >= 1 && index <= this.numberOfTasks : "Task index must be between 1 and numberOfTasks";
        String taskRemoved = taskLists.get(index - 1).toString();
        taskLists.remove(index - 1);
        numberOfTasks--;
        assert this.numberOfTasks == this.taskLists.size() : "After removal, numberOfTasks must match ArrayList size";

        StringBuilder response = new StringBuilder();
        response.append("- Noted. I've removed this task:\n");
        response.append(taskRemoved);
        response.append("\nNow you have ").append(numberOfTasks).append(" tasks in the list.");
        return response.toString();
    }

    /**
     * Marks a task as done or not done and returns a formatted response.
     *
     * @param index Position of the task in the list (1-based).
     * @param isDone true to mark as done, false to mark as not done.
     * @return Formatted message confirming the task status change.
     */
    public String getFormattedMarkResponse(int index, boolean isDone) {
        Task task = getTask(index);
        task.setDone(isDone);

        StringBuilder response = new StringBuilder();
        if (isDone) {
            response.append("- Nice! I've marked this task as done:\n");
        } else {
            response.append("- OK, I've marked this task as not done yet:\n");
        }
        response.append(task.toString());
        return response.toString();
    }

    /**
     * Returns the task at the specified position in the list.
     *
     * @param index Position of the task in the list (1-based).
     * @return The Task object at the specified position.
     */
    public Task getTask(int index) {
        assert index >= 1 && index <= this.numberOfTasks : "Task index must be between 1 and numberOfTasks (inclusive)";
        return this.taskLists.get(index - 1);
    }

    /**
     * Returns a formatted message confirming task addition.
     *
     * @param task The task that was added.
     * @return Formatted message with task details and total task count.
     */
    public String getFormattedAddResponse(Task task) {
        StringBuilder response = new StringBuilder("- Added: " + task.toString() + "\n");
        response.append("- Now you have " + numberOfTasks + " tasks in the list.");
        return response.toString();
    }

    /**
     * Adds a deadline task and returns a formatted response.
     *
     * @param taskName The name of the deadline task.
     * @param taskDate The due date of the deadline.
     * @param taskTime The due time of the deadline.
     * @return Formatted message confirming task addition.
     */
    public String addDeadlineAndGetResponse(String taskName, java.time.LocalDate taskDate, 
                                             java.time.LocalTime taskTime) {
        Deadline deadline = new Deadline(taskName, taskDate, taskTime);
        addTask(deadline);
        return getFormattedAddResponse(deadline);
    }

    /**
     * Adds an event task and returns a formatted response.
     *
     * @param taskName The name of the event task.
     * @param startDate The start date of the event.
     * @param startTime The start time of the event.
     * @param endDate The end date of the event.
     * @param endTime The end time of the event.
     * @return Formatted message confirming task addition.
     */
    public String addEventAndGetResponse(String taskName, java.time.LocalDate startDate, 
                                         java.time.LocalTime startTime, java.time.LocalDate endDate,
                                         java.time.LocalTime endTime) {
        Event event = new Event(taskName, startDate, startTime, endDate, endTime);
        addTask(event);
        return getFormattedAddResponse(event);
    }

    /**
     * Adds a todo task and returns a formatted response.
     *
     * @param taskName The name of the todo task.
     * @return Formatted message confirming task addition.
     */
    public String addTodoAndGetResponse(String taskName) {
        ToDo todo = new ToDo(taskName);
        addTask(todo);
        return getFormattedAddResponse(todo);
    }

    // Package-private accessor to allow helper classes in the same package to
    // operate on the internal task list (used by Sorter).
    ArrayList<Task> getInternalList() {
        return this.taskLists;
    }

    /**
     * Finds and returns formatted results for tasks containing the specified description.
     *
     * @param taskDescription The description to search for.
     * @return Formatted string of matching tasks, or message if none found.
     */
    public String findTask(String taskDescription) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : this.taskLists) {
            if (task.toString().contains(taskDescription)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            return "- No matching tasks found";
        } else {
            StringBuilder response = new StringBuilder("- Here are the matching tasks in your list:\n");
            for (int j = 0; j < matchingTasks.size(); j++) {
                response.append((j + 1) + ". " + matchingTasks.get(j).toString());
                if (j < matchingTasks.size() - 1) {
                    response.append("\n");
                }
            }
            return response.toString();
        }
    }
}
