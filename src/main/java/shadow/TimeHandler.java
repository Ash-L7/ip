package shadow;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A class to handle the date and time of the task
 * if it's in a different format
 */
public class TimeHandler {
    LocalDate date;
    LocalTime time;
    String dateText;
    String timeText;

    /**
     * Initializes the handler by parsing string representations of date and time.
     * Attempts to parse dates in "d/M/yyyy" format and times in "HHmm" format before falling back to ISO defaults.
     *
     * @param date
     * @param time
     */
    public TimeHandler(String date, String time) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        try {
            this.date = LocalDate.parse(date, dateFormatter);
        } catch (DateTimeParseException e) {
            this.date = LocalDate.parse(date);
        }
        assert this.date != null : "Parsed date cannot be null";

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        try {
            this.time = LocalTime.parse(time, timeFormatter);
        } catch (DateTimeParseException e) {
            this.time = LocalTime.parse(time);
        }
        assert this.time != null : "Parsed time cannot be null";

        DateTimeFormatter dateTextFormatter = DateTimeFormatter.ofPattern("LLLL yyyy");
        DateTimeFormatter timeTextFormatter = DateTimeFormatter.ofPattern("h:mm a");

        int day = this.date.getDayOfMonth();
        assert day >= 1 && day <= 31 : "Day must be between 1 and 31";
        this.dateText = day + GetDaySuffix(day) + " " + this.date.format(dateTextFormatter);
        this.timeText = this.time.format(timeTextFormatter);
    }

    /**
     * Initializes the handler using existing LocalDate and LocalTime objects.
     *
     * @param date
     * @param time
     */
    public TimeHandler(LocalDate date, LocalTime time) {
        assert date != null : "Constructor parameter date cannot be null";
        assert time != null : "Constructor parameter time cannot be null";
        this.date = date;
        this.time = time;

        DateTimeFormatter dateTextFormatter = DateTimeFormatter.ofPattern("LLLL yyyy");
        DateTimeFormatter timeTextFormatter = DateTimeFormatter.ofPattern("h:mma");

        int day = this.date.getDayOfMonth();
        assert day >= 1 && day <= 31 : "Day must be between 1 and 31";
        this.dateText = day + GetDaySuffix(day) + " " + this.date.format(dateTextFormatter);
        this.timeText = this.time.format(timeTextFormatter);
    }

    /**
     * Returns the stored LocalDate object.
     *
     * @return
     */
    public LocalDate taskDate() {
        return this.date;
    }

    /**
     * Returns the stored LocalTime object.
     *
     * @return
     */
    public LocalTime taskTime() {
        return this.time;
    }

    /**
     * Determines the ordinal suffix for a given day of the month.
     *
     * @param day
     * @return
     */
    private final String GetDaySuffix(int day) {
        switch (day) {
        case 1:
        case 21:
        case 31:
            return "st";
        case 2:
        case 22:
            return "nd";
        case 3:
        case 23:
            return "rd";
        default:
            return "th";
        }
    }

    /**
     * Returns a formatted string representation of the date and time.
     * 
     * @return
     */
    @Override
    public String toString() {
        return this.dateText + " " + this.timeText;
    }
}