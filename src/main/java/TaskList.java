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
        for (int i = 0; i < n; i++) {
            System.out.println(i + ". " + this.taskList[i].getCompletion() + this.taskList[i].getName());
        }
    }

    public Task getTask(int n) {
        return this.taskList[n];
    }
}
