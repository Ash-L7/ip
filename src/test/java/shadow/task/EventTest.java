package shadow.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testFileFormatConversion() {
        assertEquals("E, ,Project meeting,2025-02-26,12:00,2025-02-26,13:00",
                new Event("Project meeting", LocalDate.parse("2025-02-26"),
                        LocalTime.parse("12:00"), LocalDate.parse("2025-02-26"),
                        LocalTime.parse("13:00")).toFileFormat());
    }

    @Test
    public void testStringConversion() {
        assertEquals("[E][ ] Project meeting (from: 26th February 2025 12:00PM to: 26th February 2025 1:00PM)",
                new Event("Project meeting", LocalDate.parse("2025-02-26"),
                        LocalTime.parse("12:00"), LocalDate.parse("2025-02-26"),
                        LocalTime.parse("13:00")).toString());
    }
}
