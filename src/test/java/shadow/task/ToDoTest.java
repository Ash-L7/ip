package shadow.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testFileFormatConversion() {
        assertEquals("T, ,read book", new ToDo("read book").toFileFormat());
    }

    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] clean room", new ToDo("clean room").toString());
    }
}
