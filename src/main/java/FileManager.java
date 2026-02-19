import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;

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

    /** Looks for the file in the specified path
     *  Creates one if it doesn't exist.
     *
     * @param taskList The task list that will be accessed by readFile.
     */
    public void hasFile(TaskList taskList) {
        try {
            File directory = new File(DIRECTORY_PATH);

            if (!directory.exists()) {
                System.out.println("- Oops! You're missing a folder. Don't worry, I got you.");
                directory.mkdirs();
            }

            if (dataFile.createNewFile()) {
                System.out.println("- Hmm... It seems there isn't a save file. Let's get you one.");
            } else {
                System.out.println("- Welcome back. Let's get you back on track.");
                readFile(taskList);
            }
        } catch (IOException e) {
            System.out.println("- Error occurred: " + e.getMessage());
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
                String[] parts = line.split("\\|");
                String type = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String desc = parts[2].trim();

                Task task = null;
                switch (type) {
                    case "T":
                        task = new ToDo(desc);
                        break;
                    case "D":
                        task = new Deadline(desc, parts[3].trim());
                        break;
                    case "E":
                        task = new Event(desc, parts[3].trim(), parts[4].trim());
                        break;
                }
                if (task != null) {
                    task.setDone(isDone);
                    taskList.addTask(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    /**
     * Takes a TaskList object and puts the items in the object into the local save file before exiting.
     *
     * @param taskList The task list that whose items will be stored in the local file.
     */
    public void saveFile(TaskList taskList) {
//        try (FileWriter fw = new FileWriter(FILE_PATH)) {
//            for (int i = 1; i <= taskList.getSize(); i++) {
//                fw.write(taskList.getTask(i).toFileFormat() + System.lineSeparator());
//            }
//        } catch (IOException e) {
//            System.out.println("Save failed: " + e.getMessage());
//        }
    }
}
