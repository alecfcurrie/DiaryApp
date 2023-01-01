package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestProgressReport {

    private ProgressReport progressReportIncompleteParent;
    private Task taskIncomplete;
    private Task taskComplete;

    private static final String DESC = "Test report";

    @BeforeEach
    void setup() {
        taskIncomplete = new Task("Task", LocalDate.now());
        taskComplete = new Task("Task", LocalDate.now());
        progressReportIncompleteParent = new ProgressReport(DESC, taskIncomplete, false);
        ProgressReport progressReportCompleteParent = new ProgressReport(DESC, taskComplete, true);
    }

    @Test
    void testConstructor() {
        assertEquals(DESC, progressReportIncompleteParent.getDesc());
        assertEquals(taskIncomplete, progressReportIncompleteParent.getParentTask());
        assertTrue(taskIncomplete.getProgressReports().contains(progressReportIncompleteParent));
        assertFalse(taskIncomplete.isComplete());
        assertTrue(taskComplete.isComplete());
    }
}
