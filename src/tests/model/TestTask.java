package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask {

    private static final String TITLE = "Task";
    private static final LocalDate DUE_DATE = LocalDate.of(3022, 6, 9);
    Task task;

    @BeforeEach
    void setup() {
        task = new Task(TITLE, DUE_DATE);
    }

    @Test
    void testConstructor() {
        assertEquals(TITLE, task.getTitle());
        assertEquals(DUE_DATE, task.getDueDate());
    }

    @Test
    void testIsOverdue() {

    }


}
