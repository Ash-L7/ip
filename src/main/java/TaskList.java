import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskLists;
    private int n;

    public TaskList() {
        this.taskLists = new ArrayList<Task>();
        this.n = 0;
    }

    public void addTask(Task task) {
        this.taskLists.add(task);
        System.out.println("added: " + task.toString());
        System.out.println("Now you have " + (this.n + 1) + " tasks in the list.");
        n++;
    }

    public void outputTaskList() {
        for (int i = 1; i <= n; i++) {
            System.out.println(i + ". " + taskLists.get(i - 1).toString());
        }
    }

    public Task getTask(int i) {
        return this.taskLists.get(i - 1);
    }
}
