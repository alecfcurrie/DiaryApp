package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Diary {

    List<Task> toDoList;
    List<Task> completedTasks;
    List<EntryBatch> entryBatches;

    public Diary(List<EntryBatch> entryBatches, List<Task> toDoList, List<Task> completedTasks) {
        this.toDoList = toDoList;
        this.entryBatches = entryBatches;
        this.completedTasks = completedTasks;
    }

    public Diary() {
        this(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public void addEntryBatch(EntryBatch eb) {
        removeCompletedTasks(eb);
        entryBatches.add(0, eb);
    }

    public void addTask(Task t) throws IllegalArgumentException {
        if (toDoList.contains(t)) {
            throw new IllegalArgumentException("Duplicate tasks cannot be added to the Todo List");
        } else {
            int index = 0;
            LocalDate taskDueDate = t.getDueDate();
            while (index < toDoList.size()) {
                if (toDoList.get(index).compareTo(taskDueDate) <= 0) {
                    index++;
                } else {
                    toDoList.add(index, t);
                    return;
                }
            }
            toDoList.add(index, t);
        }
    }

    private void removeCompletedTasks(EntryBatch eb) {
        List<Entry> entries = eb.getEntries();
        for (Entry e: entries) {
            if (e instanceof ProgressReport) {
                ProgressReport progressReport = (ProgressReport) e;
                Task parentTask = progressReport.getParentTask();
                if (parentTask.isComplete()) {
                    completedTasks.add(parentTask);
                    toDoList.remove(parentTask);
                }
            }
        }
    }

    public List<Task> getToDoList() {
        return toDoList;
    }

    public List<EntryBatch> getEntryBatches() {
        return entryBatches;
    }

    public List<Task> getCompletedTasks() {
        return completedTasks;
    }

    public void clearCompletedTasks() {
        completedTasks = new ArrayList<>();
    }
}
