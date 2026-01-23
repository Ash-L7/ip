import java.util.Scanner;

public class Shadow {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("- Hello! I'm Shadow\n" +
                "- What can I do for you?");

        String userInput = scanner.nextLine();
        Task taskList = new Task();

        while (!userInput.equalsIgnoreCase("bye")) {
            if (userInput.equalsIgnoreCase("list")) {
                taskList.outputTaskList();
            } else {
                taskList.addTask(userInput);
            }

            userInput = scanner.nextLine();
        }

        System.out.println("- Bye. Hope to see you again soon!\n");
    }
}
