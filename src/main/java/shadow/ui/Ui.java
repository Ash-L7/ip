package shadow.ui;

import shadow.task.Task;

/**
 * Handles user interface messages and output.
 * Provides methods for displaying various types of messages to the user.
 */
public class Ui {
    /**
     * Constructs a Ui object.
     */
    public Ui() {
    }

    /**
     * Displays the greeting message to console.
     */
    public void greetings() {
        System.out.println("- Hello! I'm Shadow\n- What can I do for you?");
    }

    /**
     * Returns the greeting text for GUI display (does not print to console).
     * 
     * @return The greeting message.
     */
    public String greetingsText() {
        return "- Hello! I'm Shadow\n- What can I do for you?";
    }

    /**
     * Displays a task to the console.
     *
     * @param task The Task object to display.
     */
    public void showTask(Task task) {
        System.out.println(task.toString());
    }

    /**
     * Displays a message confirming that a task has been added.
     *
     * @param task The Task object that was added.
     * @param index The number of tasks currently in the list.
     */
    public void showTaskAdded(Task task, int index) {
        System.out.println("- Added: " + task.toString());
        System.out.println("- Now you have " + index + " tasks in the list.");
    }

    /**
     * Displays the exit message to the console.
     */
    public void onExit() {
        System.out.println("- Bye. Hope to see you again soon!");
    }

    /**
     * Displays an error message to the console.
     *
     * @param errorMessage The error message to display.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Displays a message indicating that a search is in progress.
     */
    public void showSearchProcessing() {
        System.out.println("- Attempting search...");
    }

    /**
     * Displays a message indicating that no matching tasks were found.
     */
    public void showSearchFail() {
        System.out.println("- No matching tasks found");
    }

    /**
     * Displays a message indicating that matching tasks were found.
     */
    public void showSearchSuccessful() {
        System.out.println("- Here are the matching tasks in your list:");
    }
}
