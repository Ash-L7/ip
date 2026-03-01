package shadow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for input validation and error responses from the Shadow chatbot.
 */
public class ShadowTest {
    @Test
    public void testEventIncorrectDateTimeFormat() {
        Shadow shadow = new Shadow();

        // Provide an event command where the start date uses an unsupported format "26-02-2025"
        // and an invalid time format "12:00pm" for the first time token (TimeHandler expects HHmm or ISO time)
        String response = shadow.getResponse("event project /from 26-02-2025 12:00pm /to 2025-02-26 1300");

        // Time parsing leads to a DateTimeParseException and Shadow returns a specific guidance message.
        assertTrue(response.startsWith("- Oops! Please ensure dates are in d/M/yyyy (e.g., 26/2/2025) or yyyy-MM-dd and times are in HHmm (e.g., 1430) or HH:mm."),
                "Expected a parsing guidance response when date/time format is invalid");
    }
}
