public class Ui {
    public Ui() {

    }

    public void greetings() {
        System.out.println("- Hello! I'm Shadow\n" +
                "- What can I do for you?");
    }

    public void onExit() {
        System.out.println("- Bye. Hope to see you again soon!\n");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
