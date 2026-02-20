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

    public TimeHandler(String date, String time) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        try {
            this.date = LocalDate.parse(date, dateFormatter);
        } catch (DateTimeParseException e) {
            this.date = LocalDate.parse(date);
        }

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        try {
            this.time = LocalTime.parse(time, timeFormatter);
        } catch (DateTimeParseException e) {
            this.time = LocalTime.parse(time);
        }

        DateTimeFormatter dateTextFormatter = DateTimeFormatter.ofPattern("LLLL yyyy");
        DateTimeFormatter timeTextFormatter = DateTimeFormatter.ofPattern("h:mm a");

        int day = this.date.getDayOfMonth();
        this.dateText = day + GetDaySuffix(day) + " " + this.date.format(dateTextFormatter);
        this.timeText = this.time.format(timeTextFormatter);
    }

    public TimeHandler(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;

        DateTimeFormatter dateTextFormatter = DateTimeFormatter.ofPattern("LLLL yyyy");
        DateTimeFormatter timeTextFormatter = DateTimeFormatter.ofPattern("h:mm a");

        int day = this.date.getDayOfMonth();
        this.dateText = day + GetDaySuffix(day) + " " + this.date.format(dateTextFormatter);
        this.timeText = this.time.format(timeTextFormatter);
    }

    public LocalDate taskDate() {
        return this.date;
    }

    public LocalTime taskTime() {
        return this.time;
    }

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

    @Override
    public String toString() {
        return this.dateText + " " + this.timeText;
    }
}
