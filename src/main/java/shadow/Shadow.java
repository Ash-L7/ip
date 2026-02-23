package shadow;

import java.util.Arrays;
import java.util.stream.Collectors;

import shadow.exception.InvalidCommandException;
import shadow.exception.InvalidTaskDescriptionException;
import shadow.task.TaskList;
import shadow.ui.Ui;

/**
 * The main chatbot class that handles user commands and manages task operations.
 * Coordinates between UI, task management, and file persistence.
 */
public class Shadow {
    private final FileManager dataFile;
    private final TaskList taskList;
    private final Ui ui;
    private final String startupMessage;

    /**
     * Initializes core components of the Shadow chatbot.
     * Displays a greeting message and synchronizes the task list with the local data file.
     */
    public Shadow() {
        dataFile = new FileManager();
        taskList = new TaskList();
        ui = new Ui();
        assert dataFile != null : "FileManager should be initialized";
        assert taskList != null : "TaskList should be initialized";
        assert ui != null : "Ui should be initialized";

        StringBuilder startupMessage = new StringBuilder();
        startupMessage.append(ui.greetingsText());
        String fileMsg = dataFile.hasFile(taskList);

        if (fileMsg != null && !fileMsg.isEmpty()) {
            startupMessage.append("\n");
            startupMessage.append(fileMsg);
        }

        this.startupMessage = startupMessage.toString();
        assert this.startupMessage != null : "startupMessage should not be null";
        assert !this.startupMessage.isEmpty() : "startupMessage should not be empty";
    }

    /**
     * Returns startup messages (greeting + file status) for GUI display.
     *
     * @return The startup message string.
     */
    public String getStartupMessage() {
        assert this.startupMessage != null : "Startup message should never be null";
        return this.startupMessage;
    }

    /**
     * Generates a response for the user's chat message.
     * Parses the command and delegates to appropriate command handlers.
     *
     * @param input The user's input command.
     * @return The response message to display to the user.
     */
    public String getResponse(String input) {
        assert input != null : "Input cannot be null (should be checked before calling)";
        if (input == null || input.trim().isEmpty()) {
            return "- Please enter a command.";
        }
        assert taskList != null : "taskList must be initialized";
        assert taskList.getSize() >= 0 : "taskList size cannot be negative";

        String[] action = input.split(" ");
        assert action.length > 0 : "action array must have at least one element after split";
        String cmd = action[0];
        assert cmd != null && !cmd.isEmpty() : "Command cannot be null or empty";
        StringBuilder response = new StringBuilder();

        try {
            validateCommand(cmd);

            if (cmd.equalsIgnoreCase("find")) {
                String taskDescription = Arrays.stream(action)
                        .skip(1)
                        .collect(Collectors.joining(" "));
                return taskList.findTask(taskDescription);
            }

            if (cmd.equalsIgnoreCase("list")) {
                return taskList.getFormattedTaskList();
            }

            if (cmd.equalsIgnoreCase("mark") || cmd.equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(action[1]);
                boolean status = cmd.equalsIgnoreCase("mark");
                return taskList.getFormattedMarkResponse(index, status);
            }

            if (cmd.equalsIgnoreCase("delete")) {
                int index = Integer.parseInt(action[1]);
                return taskList.getFormattedRemoveResponse(index);
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
                return taskList.addDeadlineAndGetResponse(taskName.toString().trim(), 
                        timeHandler.taskDate(), timeHandler.taskTime());
            }

            if (cmd.equalsIgnoreCase("event")) {
                validateTaskDescription(action);
                StringBuilder taskName = new StringBuilder();
                boolean isTaskName = true;

                assert taskList.getSize() > 0 : "taskList should have at least one task when size > 0";
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

                TimeHandler startTime = new TimeHandler(action[startIndex], action[startIndex + 1]);
                TimeHandler endTime = new TimeHandler(action[endIndex], action[endIndex + 1]);

                return taskList.addEventAndGetResponse(taskName.toString().trim(), 
                        startTime.taskDate(), startTime.taskTime(),
                        endTime.taskDate(), endTime.taskTime());
            }

            if (cmd.equalsIgnoreCase("todo")) {
                validateTaskDescription(action);
                StringBuilder taskName = new StringBuilder();

                for (int i = 1; i < action.length; i++) {
                    taskName.append(action[i]);
                    taskName.append(" ");
                }

                return taskList.addTodoAndGetResponse(taskName.toString().trim());
            }

            if (cmd.equalsIgnoreCase("bye")) {
                dataFile.saveFile(taskList);
                ui.onExit();
                return "- Bye. Hope to see you again soon!";
            }

        } catch (InvalidCommandException e) {
            assert e.getMessage() != null : "Exception message should not be null";
            return e.getMessage();
        } catch (InvalidTaskDescriptionException e) {
            assert e.getMessage() != null : "Exception message should not be null";
            return e.getMessage();
        } catch (Exception e) {
            assert e.getMessage() != null : "Exception should have a message";
            return "- Oops! Something went wrong: " + e.getMessage();
        }

        return "- I couldn't parse that command.";
    }

    /**
     * Validates whether the command is recognized.
     *
     * @param command The command string to validate.
     * @throws InvalidCommandException if command is not valid.
     */
    public static void validateCommand(String command) throws InvalidCommandException {
        String[] validCommands = {"todo", "deadline", "event", "list", "delete", "mark", "unmark", "find", "bye"};
        for (String cmd : validCommands) {
            if (command.equalsIgnoreCase(cmd)) {
                return;
            }
        }
        throw new InvalidCommandException("- Well that's strange. You sure I should be able to do that?");
    }

    /**
     * Validates that a task description is provided.
     *
     * @param userInput The parsed user input array.
     * @throws InvalidTaskDescriptionException if no description is provided.
     */
    public static void validateTaskDescription(String[] userInput) throws InvalidTaskDescriptionException {
        if (userInput.length < 2) {
            throw new InvalidTaskDescriptionException("- Can't be doing nothing, could you?");
        }
    }
}
