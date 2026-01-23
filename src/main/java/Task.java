public class Task {
    private String[] taskList;
    private int n;

    public Task() {
        this.taskList = new String[100];
        this.n = 0;
    }

    public void addTask(String task) {
        this.taskList[n] = task;
        System.out.println("added: " + task);
        n++;
    }

    public void outputTaskList() {
        for (int i = 0; i < n; i++) {
            System.out.println(i + ". " + this.taskList[i]);
        }
    }
}
