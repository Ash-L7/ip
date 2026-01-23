import java.util.Scanner;

public class Shadow {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo = "";
        System.out.println("- Hello! I'm Shadow\n" +
                "- What can I do for you?");

        String userInput = scanner.nextLine();

        while (!userInput.equals("Bye")) {
            System.out.println("- " + userInput);
            userInput = scanner.nextLine();
        }

        System.out.println("- Bye. Hope to see you again soon!\n");
    }
}
