package shadow.task;

import java.util.ArrayList;

/**
 * Utility class that provides sorting helpers for TaskList
 */
public class Sorter {

    /**
     * Private constructor to prevent instantiation. This class only exposes
     * static helper methods and should not be instantiated.
     */
    private Sorter() {
        // static helper class
    }

    /**
     * Sorts all tasks in the given grouping by type in the order
     * Deadline, Event, ToDo. Within each group, tasks are ordered using
     * the type-appropriate comparator (deadlines by due date/time, events by
     * start date/time, todos alphabetically). The method mutates the internal
     * ordering of taskList.
     *
     * @param taskList the task list to be fully sorted; must not be {@code null}
     * @return a formatted string describing the newly ordered task list
     */
    public static String sortAll(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getInternalList();
        tasks.sort((a, b) -> {
            int typeA = (a instanceof Deadline) ? 0 : (a instanceof Event) ? 1 : 2;
            int typeB = (b instanceof Deadline) ? 0 : (b instanceof Event) ? 1 : 2;
            if (typeA != typeB) {
                return Integer.compare(typeA, typeB);
            }
            if (a instanceof Deadline && b instanceof Deadline) {
                Deadline da = (Deadline) a;
                Deadline db = (Deadline) b;
                int dateCmp = da.getDeadlineDate().compareTo(db.getDeadlineDate());
                if (dateCmp != 0) {
                    return dateCmp;
                }
                return da.getDeadlineTime().compareTo(db.getDeadlineTime());
            }
            if (a instanceof Event && b instanceof Event) {
                Event ea = (Event) a;
                Event eb = (Event) b;
                int dateCmp = ea.getStartDate().compareTo(eb.getStartDate());
                if (dateCmp != 0) {
                    return dateCmp;
                }
                return ea.getStartTime().compareTo(eb.getStartTime());
            }
            if (a instanceof ToDo && b instanceof ToDo) {
                return a.name.compareToIgnoreCase(b.name);
            }
            return 0;
        });
        return "- Sorted all tasks (deadlines, events, todos):\n" + taskList.getFormattedTaskList();
    }
}
