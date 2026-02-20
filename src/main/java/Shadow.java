import java.util.Scanner;
import java.lang.StringBuilder;

public class Shadow {
    /** A collection of all valid commands for the chatbot Shadow. */
    private static final String[] validCommands = {"todo", "deadline", "event", "list", "delete", "mark", "unmark",
            "bye"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileManager dataFile = new FileManager();
        TaskList taskList = new TaskList();

        System.out.println("- Hello! I'm Shadow\n" +
                "- What can I do for you?");

        dataFile.hasFile(taskList);

        String userInput = scanner.nextLine();
        String[] action = userInput.split(" ");
        int index;

        while (!action[0].equalsIgnoreCase("bye")) {
            String cmd = action[0];

            try {
                validateCommand(cmd);

                if (action[0].equalsIgnoreCase("list")) {
                    taskList.outputTaskList();
                }

                if (action[0].equalsIgnoreCase("mark")) {
                    index = Integer.parseInt(action[1]);
                    taskList.getTask(index).setDone(true);
                }

                if (action[0].equalsIgnoreCase("unmark")) {
                    index = Integer.parseInt(action[1]);
                    taskList.getTask(index).setDone(false);
                }

                if (action[0].equalsIgnoreCase("delete")) {
                    index = Integer.parseInt(action[1]);
                    taskList.removeTask(index);
                }

                if (action[0].equalsIgnoreCase("deadline")) {
                    validateTaskDescription(action);
                    StringBuilder taskName = new StringBuilder();
                    int timeIndex = 0;

                    for (int i = 1; i < action.length; i++) {
                        if (action[i].equals("/by")) {
                            timeIndex = i + 1;
                            break;
                        } else {
                            taskName.append(action[i]);
                            taskName.append(" ");
                        }
                    }

                    TimeHandler timeHandler = new TimeHandler(action[timeIndex], action[timeIndex + 1]);

                    Deadline deadline = new Deadline(taskName.toString().trim(), timeHandler.taskDate(),
                            timeHandler.taskTime());
                    taskList.addTask(deadline);
                }

                if (action[0].equalsIgnoreCase("event")) {
                    validateTaskDescription(action);
                    StringBuilder taskName = new StringBuilder();
                    boolean isTaskName = true;
                    int startIndex = 0;
                    int endIndex = 0;

                    for (int i = 1; i < action.length; i++) {
                        if (action[i].equals("/from")) {
                            isTaskName = false;
                            startIndex = i + 1;

                            for (i = i + 1; i < action.length; i++) {
                                if (action[i].equals("/to")) {
                                    endIndex = i + 1;
                                    break;
                                }
                            }
                        } else {
                            if (isTaskName) {
                                taskName.append(action[i]);
                                taskName.append(" ");
                            }
                        }
                    }

                    TimeHandler startTine = new TimeHandler(action[startIndex], action[startIndex + 1]);
                    TimeHandler endTime = new TimeHandler(action[endIndex], action[endIndex + 1]);

                    Event event = new Event(taskName.toString().trim(), startTine.taskDate(), startTine.taskTime(),
                            endTime.taskDate(), endTime.taskTime());
                    taskList.addTask(event);
                }

                if (action[0].equalsIgnoreCase("todo")) {
                    validateTaskDescription(action);
                    StringBuilder taskName = new StringBuilder();

                    for (int i = 1; i < action.length; i++) {
                        taskName.append(action[i]);
                        taskName.append(" ");
                    }

                    ToDo toDo = new ToDo(taskName.toString().trim());
                    taskList.addTask(toDo);
                }

            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            } catch (InvalidTaskDescriptionException e) {
                System.out.println(e.getMessage());
            } finally {
                userInput = scanner.nextLine();
                action = userInput.split(" ");
            }
        }

        dataFile.saveFile(taskList);

        System.out.println("- Bye. Hope to see you again soon!\n");
    }

    /**
     * Checks whether the command entered by user
     * is among the list of valid commands.
     * Informs the user of invalid command.
     *
     * @param command Action the user would like Shadow to perform.
     * @throws InvalidCommandException
     */
    public static void validateCommand(String command) throws InvalidCommandException {
        for (String cmd : validCommands) {
            if (command.equalsIgnoreCase(cmd)) {
                return ;
            }
        }

        throw new InvalidCommandException("- Well that's strange. You sure I should be able to do that?");
    }

    /**
     * Checks whether a description for the task is given
     * by checking the length of the input.
     *
     * @param userInput String array of the user's input.
     * @throws InvalidTaskDescriptionException
     */
    public static void validateTaskDescription(String[] userInput) throws InvalidTaskDescriptionException {
        if (userInput.length < 2) {
            throw new InvalidTaskDescriptionException("- Can't be doing nothing, could you?");
        }
    }
}
