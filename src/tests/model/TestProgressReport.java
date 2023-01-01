package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestProgressReport {

    private ProgressReport progressReport;
    private Task task;
    private EntryBatch eb;

    private static final String DESC = "Test report";

    @BeforeEach
    void setup() {
        task = new Task("Task", LocalDate.now());
        eb = new EntryBatch();
        progressReport = new ProgressReport(DESC, task, eb);
    }

    @Test
    void testConstructor() {
        assertEquals(DESC, progressReport.getDesc());
        assertEquals(task, progressReport.getParentTask());
        assertEquals(eb, progressReport.getParentEntryBatch());
        assertTrue(task.getProgressReports().contains(progressReport));
    }
}
