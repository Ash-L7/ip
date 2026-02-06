import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;

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
            } else if (action[0].equalsIgnoreCase("delete")) {
                index = Integer.parseInt(action[1]);
                taskList.removeTask(index);
            } else if (action[0].equalsIgnoreCase("deadline")) {
                StringBuilder taskName = new StringBuilder();
                StringBuilder taskDeadline = new StringBuilder();

                for (int i = 1; i < action.length; i++) {
                    if (action[i].equals("/by")) {
                        for (i = i + 1; i < action.length; i++) {
                            taskDeadline.append(" ");
                            taskDeadline.append(action[i]);
                        }
                        break;
                    } else {
                        taskName.append(action[i]);
                        taskName.append(" ");
                    }
                }

                Deadline deadline = new Deadline(taskName.toString(), taskDeadline.toString());
                taskList.addTask(deadline);
            } else if (action[0].equalsIgnoreCase("event")) {
                StringBuilder taskName = new StringBuilder();
                StringBuilder startTime = new StringBuilder();
                StringBuilder endTime = new StringBuilder();
                boolean isTaskName = true;

                for (int i = 1; i < action.length; i++) {
                    if (action[i].equals("/from")) {
                        for (i = i + 1; i < action.length; i++) {
                            if (action[i].equals("/to")) {
                                i = i - 2;
                                break;
                            }
                            startTime.append(" ");
                            startTime.append(action[i]);
                        }
                    } else if (action[i].equals("/to")) {
                        for (i = i + 1; i < action.length; i++) {
                            endTime.append(" ");
                            endTime.append(action[i]);
                        }
                    } else {
                        if (isTaskName) {
                            taskName.append(action[i]);
                            taskName.append(" ");
                        }
                    }
                }

                Event event = new Event(taskName.toString(), startTime.toString(), endTime.toString());
                taskList.addTask(event);
            } else {
                StringBuilder taskName = new StringBuilder();

                for (int i = 1; i < action.length; i++) {
                    taskName.append(action[i]);
                    taskName.append(" ");
                }

                ToDo toDo = new ToDo(taskName.toString());
                taskList.addTask(toDo);
            }

            userInput = scanner.nextLine();
            action = userInput.split(" ");
        }

        System.out.println("- Bye. Hope to see you again soon!\n");
    }
}
