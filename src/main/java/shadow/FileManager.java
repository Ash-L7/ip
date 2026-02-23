package shadow;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import shadow.task.Task;
import shadow.task.Deadline;
import shadow.task.Event;
import shadow.task.TaskList;
import shadow.task.ToDo;

/**
 * Manages file I/O operations for persisting task data to a CSV file.
 * Handles reading tasks from file on startup and writing tasks to file on exit.
 */
public class FileManager {
    private static final String FILE_PATH = "./data/tasklist.csv";
    private static final String DIRECTORY_PATH = "./data/";
    private final File dataFile;

    /**
     * Constructor for the file manager.
     */
    public FileManager() {
        this.dataFile = new File(FILE_PATH);
    }

    /**
     * Ensures the data directory and file exist. Reads the file into the provided taskList if present.
     * Returns a startup message suitable for GUI display.
     *
     * @param taskList The task list that will be accessed by readFile.
     * @return startup message describing the file status
     */
    public String hasFile(TaskList taskList) {
        assert taskList != null : "taskList parameter cannot be null";
        try {
            File directory = new File(DIRECTORY_PATH);

            if (!directory.exists()) {
                directory.mkdirs();
                assert directory.exists() : "Directory should exist after mkdirs() call";
                if (dataFile.createNewFile()) {
                    return "- Oops! You're missing a folder. Don't worry, I got you.\n- Hmm... It seems there isn't a save file. Let's get you one.";
                }
            }

            if (dataFile.createNewFile()) {
                assert dataFile.exists() : "File should exist after createNewFile()";
                return "- Hmm... It seems there isn't a save file. Let's get you one.";
            } else {
                assert dataFile.exists() : "File must exist if createNewFile() returned false";
                readFile(taskList);
                assert taskList.getSize() >= 0 : "taskList size cannot be negative after reading file";
                return "- Welcome back. Let's get you back on track.";
            }
        } catch (IOException e) {
            return "- Error occurred: " + e.getMessage();
        }
    }

    /**
     * Reads the file and fills the task list with items in the file.
     *
     * @param taskList The task list that will be accessed and used to record tasks.
     */
    public void readFile(TaskList taskList) {
        try (Scanner s = new Scanner(dataFile)) {
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] parts = line.split(",");
                String type = parts[0].trim();
                boolean isDone = parts[1].trim().equals("X");
                String desc = parts[2].trim();

                Task task = null;

                switch (type) {
                    case "T":
                        task = new ToDo(desc);
                        break;
                    case "D":
                        TimeHandler timeHandler = new TimeHandler(parts[3].trim(), parts[4].trim());
                        task = new Deadline(desc, timeHandler.taskDate(), timeHandler.taskTime());
                        break;
                    case "E":
                        TimeHandler startTime = new TimeHandler(parts[3].trim(), parts[4].trim());
                        TimeHandler endTime = new TimeHandler(parts[5].trim(), parts[6].trim());
                        task = new Event(desc, startTime.taskDate(), startTime.taskTime(),
                                endTime.taskDate(), endTime.taskTime());
                        break;
                }

                if (task != null) {
                    taskList.addTask(task);
                    task.setDone(isDone);
                }
            }
        } catch (FileNotFoundException e) {
            // File not found is acceptable on first run
        }
    }

    /**
     * Saves all tasks from the taskList to the local data file.
     *
     * @param taskList The task list whose items will be stored in the local file.
     */
    public void saveFile(TaskList taskList) {
        try (FileWriter fw = new FileWriter(FILE_PATH)) {
            for (int i = 1; i <= taskList.getSize(); i++) {
                fw.write(taskList.getTask(i).toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            // Log save failure silently to avoid interrupting user
        }
    }
}