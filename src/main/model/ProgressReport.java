package model;

public class ProgressReport extends Entry{

    private Task parentTask;

    private EntryBatch parentEntryBatch;

    public ProgressReport(String desc, Task parentTask, EntryBatch parentEntryBatch) {
        super(parentTask.getTitle(), desc);
        this.parentTask = parentTask;
        this.parentEntryBatch = parentEntryBatch;
        parentTask.addProgressReport(this);
    }

    public Task getParentTask() {
        return parentTask;
    }

    public EntryBatch getParentEntryBatch() {
        return parentEntryBatch;
    }
}
