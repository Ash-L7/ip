public class TaskList {
    private Task[] taskList;
    private int n;

    public TaskList() {
        this.taskList = new Task[100];
        this.n = 0;
    }

    public void addTask(Task task) {
        this.taskList[n] = task;
        System.out.println("added: " + task.getName());
        n++;
    }

    public void outputTaskList() {
        for (int i = 1; i <= n; i++) {
            System.out.println(i + ". " + taskList[i - 1].toString());
        }
    }

    public Task getTask(int i) {
        return this.taskList[i - 1];
    }
}
