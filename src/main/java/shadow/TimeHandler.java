package shadow;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles parsing and formatting of date and time for tasks.
 * Supports multiple date and time formats, with fallback to ISO 8601 format.
 */
public class TimeHandler {
    /** The parsed LocalDate. */
    private LocalDate date;
    /** The parsed LocalTime. */
    private LocalTime time;
    /** Formatted date text for display (e.g., "23rd February 2026"). */
    private final String dateText;
    /** Formatted time text for display (e.g., "2:30 PM"). */
    private final String timeText;

    /**
     * Constructs a TimeHandler by parsing string representations of date and time.
     * Attempts to parse dates in "d/M/yyyy" format and times in "HHmm" format.
     * Falls back to ISO 8601 format if custom formats fail.
     *
     * @param dateString The date as a String (e.g., "23/2/2026" or "2026-02-23").
     * @param timeString The time as a String (e.g., "1430" or "14:30").
     */
    public TimeHandler(String dateString, String timeString) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        try {
            this.date = LocalDate.parse(dateString, dateFormatter);
        } catch (DateTimeParseException e) {
            this.date = LocalDate.parse(dateString);
        }

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        try {
            this.time = LocalTime.parse(timeString, timeFormatter);
        } catch (DateTimeParseException e) {
            this.time = LocalTime.parse(timeString);
        }

        DateTimeFormatter dateTextFormatter = DateTimeFormatter.ofPattern("LLLL yyyy");
        DateTimeFormatter timeTextFormatter = DateTimeFormatter.ofPattern("h:mm a");

        int day = this.date.getDayOfMonth();
        this.dateText = day + getDaySuffix(day) + " " + this.date.format(dateTextFormatter);
        this.timeText = this.time.format(timeTextFormatter);
    }

    /**
     * Constructs a TimeHandler with existing LocalDate and LocalTime objects.
     *
     * @param date The LocalDate object.
     * @param time The LocalTime object.
     */
    public TimeHandler(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;

        DateTimeFormatter dateTextFormatter = DateTimeFormatter.ofPattern("LLLL yyyy");
        DateTimeFormatter timeTextFormatter = DateTimeFormatter.ofPattern("h:mm a");

        int day = this.date.getDayOfMonth();
        this.dateText = day + getDaySuffix(day) + " " + this.date.format(dateTextFormatter);
        this.timeText = this.time.format(timeTextFormatter);
    }

    /**
     * Returns the stored LocalDate object.
     *
     * @return The LocalDate object.
     */
    public LocalDate taskDate() {
        return this.date;
    }

    /**
     * Returns the stored LocalTime object.
     *
     * @return The LocalTime object.
     */
    public LocalTime taskTime() {
        return this.time;
    }

    /**
     * Determines the ordinal suffix for a given day of the month (st, nd, rd, th).
     *
     * @param day The day of the month.
     * @return The ordinal suffix (e.g., "st" for 1st, "nd" for 2nd).
     */
    private String getDaySuffix(int day) {
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
     * Format: "23rd February 2026 2:30 PM"
     *
     * @return Formatted date and time string.
     */
    @Override
    public String toString() {
        return this.dateText + " " + this.timeText;
    }
}