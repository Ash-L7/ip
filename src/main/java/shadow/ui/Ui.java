package shadow.ui;

import shadow.task.Task;

public class Ui {
    public Ui() {
    }

    public void greetings() {
        System.out.println("- Hello! I'm shadow.Shadow\n"
                + "- What can I do for you?");
    }

    public void showTask(Task task) {
        System.out.println(task.toString());
    }

    public void showTaskAdded(Task task, int index) {
        System.out.println("- Added: " + task.toString());
        System.out.println("- Now you have " + index + " tasks in the list.");
    }

    public void onExit() {
        System.out.println("- Bye. Hope to see you again soon!");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showSearchProcessing() {
        System.out.println("- Attempting search...");
    }

    public void showSearchFail() {
        System.out.println("- No matching tasks found");
    }

    public void showSearchSuccessful() {
        System.out.println("- Here are the matching tasks in your list:");
    }
}
