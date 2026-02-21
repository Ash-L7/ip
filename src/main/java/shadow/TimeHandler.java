package shadow;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A class to handle the date and time of the task
 * if it's in a different format
 */
public class TimeHandler {
    String date;
    String time;
    String dayText;

    public TimeHandler(LocalDate date, LocalTime time) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("L yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
        int day = date.getDayOfMonth();

        this.date = day + GetDaySuffix(day) + date.format(dateFormatter);
        this.time = time.format(timeFormatter);
        this.dayText = date.getDayOfWeek().name();
    }

    public String convertToDate() {
        return date;
    }

    public String convertToFullDate() {
        return date + " " + time;
    }

    public String convertToTime() {
        return time;
    }

    public String convertToDay() {
        return dayText;
    }

    public String convertToDayTime() {
        return dayText + " " + time;
    }

    private final String GetDaySuffix(int day)
    {
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
}
