package shadow;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import shadow.exception.InvalidCommandException;
import shadow.exception.InvalidTaskDescriptionException;
import shadow.task.TaskList;
import shadow.task.ToDo;
import shadow.task.Deadline;
import shadow.task.Event;
import shadow.task.Task;
import shadow.ui.Ui;
import shadow.command.FindCommand;
import shadow.command.AddDeadlineCommand;
import shadow.command.AddEventCommand;
import shadow.command.AddToDoCommand;


public class Shadow {
    /** A collection of all valid commands for the chatbot shadow.Shadow. */
    private static final String[] validCommands = {"todo", "deadline", "event", "list", "delete", "mark", "unmark",
            "find", "bye"};
    private final FileManager dataFile;
    private final TaskList taskList;
    private final Ui ui;
    private final String startupMessage;

    /**
     * Initializes core components of the chatbot.
     * Displays a greeting message.
     * Synchronizes task list with local data file.
     */
    public Shadow() {
        dataFile = new FileManager();
        taskList = new TaskList();
        ui = new Ui();

        StringBuilder startupMessage = new StringBuilder();
        startupMessage.append(ui.greetingsText());
        String fileMsg = dataFile.hasFile(taskList);

        if (fileMsg != null && !fileMsg.isEmpty()) {
            startupMessage.append("\n");
            startupMessage.append(fileMsg);
        }

        this.startupMessage = startupMessage.toString();
    }

    /** Returns startup messages (greeting + file status) for GUI display. */
    public String getStartupMessage() {
        return this.startupMessage;
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "- Please enter a command.";
        }

        String[] action = input.split(" ");
        String cmd = action[0];
        StringBuilder response = new StringBuilder();

        try {
            validateCommand(cmd);

            if (cmd.equalsIgnoreCase("find")) {
                String taskDescription = Arrays.stream(action)
                        .skip(1)
                        .collect(Collectors.joining(" "));

                java.util.ArrayList<Task> matchingTasks = new java.util.ArrayList<>();
                for (int i = 1; i <= taskList.getSize(); i++) {
                    Task t = taskList.getTask(i);
                    if (t.toString().contains(taskDescription)) {
                        matchingTasks.add(t);
                    }
                }

                if (matchingTasks.isEmpty()) {
                    response.append("- No matching tasks found");
                } else {
                    response.append("- Here are the matching tasks in your list:\n");
                    for (int j = 0; j < matchingTasks.size(); j++) {
                        response.append((j + 1) + ". " + matchingTasks.get(j).toString());
                        if (j < matchingTasks.size() - 1) {
                            response.append("\n");
                        }
                    }
                }

                return response.toString();
            }

            if (cmd.equalsIgnoreCase("list")) {
                if (taskList.getSize() == 0) {
                    return "- No tasks in the list.";
                }

                for (int i = 1; i <= taskList.getSize(); i++) {
                    response.append(i + ". " + taskList.getTask(i).toString());
                    if (i < taskList.getSize()) {
                        response.append("\n");
                    }
                }

                return response.toString();
            }

            if (cmd.equalsIgnoreCase("mark") || cmd.equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(action[1]);
                boolean status = cmd.equalsIgnoreCase("mark");
                Task t = taskList.getTask(index);
                t.setDone(status);

                if (status) {
                    response.append("- Nice! I've marked this task as done:\n");
                } else {
                    response.append("- OK, I've marked this task as not done yet:\n");
                }

                response.append(t.toString());
                return response.toString();
            }

            if (cmd.equalsIgnoreCase("delete")) {
                int index = Integer.parseInt(action[1]);
                String taskRemoved = taskList.getTask(index).toString();
                taskList.removeTask(index);

                response.append("- Noted. I've removed this task:\n");
                response.append(taskRemoved).append("\nNow you have ").append(taskList.getSize())
                        .append(" tasks in the list.");
                return response.toString();
            }

            if (cmd.equalsIgnoreCase("deadline")) {
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

                response.append("- Added: " + deadline.toString() + "\n");
                response.append("- Now you have " + taskList.getSize() + " tasks in the list.");
                return response.toString();
            }

            if (cmd.equalsIgnoreCase("event")) {
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

                response.append("- Added: " + event.toString() + "\n");
                response.append("- Now you have " + taskList.getSize() + " tasks in the list.");
                return response.toString();
            }

            if (cmd.equalsIgnoreCase("todo")) {
                validateTaskDescription(action);
                StringBuilder taskName = new StringBuilder();

                for (int i = 1; i < action.length; i++) {
                    taskName.append(action[i]);
                    taskName.append(" ");
                }

                ToDo toDo = new ToDo(taskName.toString().trim());
                taskList.addTask(toDo);

                response.append("- Added: " + toDo.toString() + "\n");
                response.append("- Now you have " + taskList.getSize() + " tasks in the list.");
                return response.toString();
            }

            if (cmd.equalsIgnoreCase("bye")) {
                dataFile.saveFile(taskList);
                ui.onExit();
                return "- Bye. Hope to see you again soon!";
            }

        } catch (InvalidCommandException e) {
            return e.getMessage();
        } catch (InvalidTaskDescriptionException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "- Oops! Something went wrong: " + e.getMessage();
        }

        return "- I couldn't parse that command.";
    }

    /**
     * Checks whether the command entered by user
     * is among the list of valid commands.
     * Informs the user of invalid command.
     *
     * @param command Action the user would like shadow.Shadow to perform.
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
