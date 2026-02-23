# Shadow User Guide

![Product screenshot](https://ash-l7.github.io/ip/Ui.png)

Shadow is a task management chatbot, acting just like your shadow, helping you remember your tasks.

## Command Summary

| Command  | Format                           | Example                                                      |
|----------|----------------------------------|--------------------------------------------------------------|
| Todo     | `todo TASK`                      | `todo read book`                                             |
| Deadline | `deadline TASK /by DATE`         | `deadline Return book /by 2026-02-23 23:59`                  |
| Event    | `event TASK /from START /to END` | `event Rockfest /from 2026-03-09 18:00 /to 2026-03-10 00:00` |
| List     | `list`                           |                                                              |
| Find     | `find TASK_DESCRIPTION`          | `find fest`                                                  |
| Mark     | `mark TASK_NUMBER`               | `mark 2`                                                     |
| Unmark   | `unmark TASK_NUMBER`             | `unmark 3`                                                   |
| Delete   | `delete TASK_NUMBER`             | `delete 1`                                                   |
| Sort     | `sort`                           |                                                              |
| Bye      | `bye`                            |                                                              |

## Adding todo tasks

Adds a generic todo task.

**Format:** `todo TASK`

**Example:** `todo read book`

## Adding deadline tasks

Adds a task with a deadline.

**Format:** `deadline TASK /by DATE TIME`

- DATE must be in the format `yyyy-mm-dd`
- TIME must be in the format `HH:mm`

**Example:** `deadline Return book /by 2026-02-23 23:59`

## Adding event tasks

Adds a task occurring over a period of time.

**Format:** `event TASK /from START_DATE START_TIME /to END_DATE END_TIME`

- START/END must be in the format `yyyy-mm-dd`
- TIME must be in the format `HH:mm`

**Example:** `event Rockfest /from 2026-03-09 18:00 /to 2026-03-10 00:00`

## Listing tasks

Displays every task in the task list.

**Format:** `list`

## Finding tasks

Finds tasks which contain the given description.

**Format:** `find TASK_DESCRIPTION`

- Tasks whose description is a superstring of TASK_DESCRIPTION will be shown

**Example:** `find book`

## Marking tasks

Marks a task as complete.

**Format:** `mark TASK_NUMBER`

- TASK_NUMBER must not be greater than the number of tasks in the list
- A task which is already marked will remain marked

**Example:** `mark 2`

## Unmarking tasks

Marks a task as incomplete.

**Format:** `unmark TASK_NUMBER`

- TASK_NUMBER must not be greater than the number of tasks in the list
- A task which is not yet marked will remain unmarked

**Example:** `unmark 3`

## Deleting tasks

Deletes a task from the task list.

**Format:** `delete TASK_NUMBER`

- TASK_NUMBER must not be greater than the number of tasks in the list

**Example:** `delete 1`

## Sorting tasks

Sorts all tasks in the given grouping by type in the order: Deadline, Event, ToDo.
- Deadlines are sorted by due date/time
- Events are sorted by start date/time
- ToDos are sorted alphabetically

**Format:** `sort`

## Closing the program

Closes the program after a brief delay.

**Format:** `bye`
