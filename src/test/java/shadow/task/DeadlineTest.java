package shadow.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testFileFormatConversion() {
        assertEquals("D, ,return book,2025-02-22,23:59",
                new Deadline("return book", LocalDate.parse("2025-02-22"),
                        LocalTime.parse("23:59")).toFileFormat());
    }

    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] return book (by: 22nd February 2025 11:59PM)",
                new Deadline("return book", LocalDate.parse("2025-02-22"),
                        LocalTime.parse("23:59")).toString());
    }
}
