import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskLists;
    private int numberOfTasks;

    public TaskList() {
        this.taskLists = new ArrayList<Task>();
        this.numberOfTasks = 0;
    }

    public void addTask(Task task) {
        this.taskLists.add(task);
        System.out.println("added: " + task.toString());
        System.out.println("Now you have " + (this.numberOfTasks + 1) + " tasks in the list.");
        numberOfTasks++;
    }

    public void outputTaskList() {
        for (int i = 1; i <= numberOfTasks; i++) {
            System.out.println(i + ". " + taskLists.get(i - 1).toString());
        }
    }

    public void removeTask(int index) {
        String taskRemoved = taskLists.get(index - 1).toString();
        taskLists.remove(index - 1);

        System.out.println("Noted. I've removed this task: \n" +
                taskRemoved + "\nNow you have " +
                numberOfTasks + " tasks in the list.");
    }

    public Task getTask(int i) {
        return this.taskLists.get(i - 1);
    }
}
