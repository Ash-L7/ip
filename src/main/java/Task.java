public class Task {
    private String name;
    private boolean isDone;
    private String[] taskList;
    private int n;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public String getIsDone() {
        if (this.isDone) {
            return "X";
        }

        return " ";
    }

    public void setDone(boolean status) {
        if (status) {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(this.toString());
        } else {
            this.isDone = false;
            System.out.println("OK, I've marked this task as not done yet: ");
            System.out.println(this.toString());
        }
    }

    @Override
    public String toString() {
        return "[" + getIsDone() + "] " + this.name;
    }
}
