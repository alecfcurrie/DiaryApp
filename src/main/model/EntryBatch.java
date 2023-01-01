package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EntryBatch {

    private LocalDateTime entryTime;
    private List<Entry> entries;

    private static final String COMPLETION_STRING =  "Finished task: ";

    public EntryBatch() {
        this(new ArrayList<>());
    }
    public EntryBatch(List<Entry> entries) {
        entryTime = LocalDateTime.now();
        this.entries = entries;
    }

    public void addActivity(Activity a) {
        entries.add(a);
    }

    public void removeEntry(Entry e) {
        for(Entry entry : entries) {
            if (entry.equals(e)) {
                entries.remove(e);
            }
        }
    }

    public void addCompleteTaskEntry(Task t) {
        Entry e = new ProgressReport(COMPLETION_STRING + t.getTitle(), t, this);
        entries.add(e);
        t.complete();
    }

    public void addTaskProgressReport(Task t, String msg) {
        Entry e = new ProgressReport(msg, t, this);
        entries.add(e);
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public List<Entry> getEntries() {
        return entries;
    }
}
