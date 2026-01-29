import java.util.Scanner;

public class Shadow {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("- Hello! I'm Shadow\n" +
                "- What can I do for you?");

        String userInput = scanner.nextLine();
        String[] action = userInput.split(" ");
        TaskList taskList = new TaskList();
        Integer index = 0;

        while (!action[0].equalsIgnoreCase("bye")) {
            if (action[0].equalsIgnoreCase("list")) {
                taskList.outputTaskList();
            } else if (action[0].equalsIgnoreCase("mark")) {
                index = Integer.parseInt(action[1]);
                taskList.getTask(index).setDone(true);
            } else if (action[0].equalsIgnoreCase("unmark")) {
                index = Integer.parseInt(action[1]);
                taskList.getTask(index).setDone(false);
            } else {
                Task task = new Task(userInput);
                taskList.addTask(task);
            }

            userInput = scanner.nextLine();
            action = userInput.split(" ");
        }

        System.out.println("- Bye. Hope to see you again soon!\n");
    }
}
