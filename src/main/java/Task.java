public class Task {
    private String name;
    private String completion;
    private String[] taskList;
    private int n;

    public Task(String name) {
        this.name = name;
        this.completion = "[ ] ";
    }

    public String getName() {
        return this.name;
    }

    public String getCompletion() {
        return this.completion;
    }

    public void markCompletion(boolean status) {
        if (status) {
            this.completion = "[X] ";
            System.out.println("Nice! I've marked this task as done: " + this.completion + this.name);
        } else {
            this.completion = "[ ] ";
            System.out.println("OK, I've marked this task as not done yet: " + this.completion + this.name);
        }
    }
}
