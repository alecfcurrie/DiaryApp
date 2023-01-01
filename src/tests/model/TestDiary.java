package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestDiary {

    Task task1 = new Task("Task1", LocalDate.of(2000, 1, 1));
    Task task2 = new Task("Task2", LocalDate.of(2000, 2, 1));
    Task task3_1 = new Task("Task3_1", LocalDate.of(2000, 2, 4));
    Task task3_2 = new Task("Task3_2", LocalDate.of(2000, 2, 4));
    Task task4 = new Task("Task4", LocalDate.of(2000, 4, 1));
    Task task5 = new Task("Task5", LocalDate.of(2000, 6, 1));

    Activity activity1 = new Activity("Act1", "Act1", null);
    Activity activity2 = new Activity("Act2", "Act2", null);
    Activity activity3 = new Activity("Act3", "Act3", null);

    EntryBatch eb1;
    EntryBatch eb2;
    EntryBatch eb3;

    Diary testDiary;

    @BeforeEach
    void setup() {
        testDiary = new Diary();
        eb1 = new EntryBatch();
        eb3 = new EntryBatch();
        eb2 = new EntryBatch();

        eb1.addCompleteTaskEntry(task1);
        eb1.addCompleteTaskEntry(task2);

        eb2.addCompleteTaskEntry(task3_1);
        eb2.addCompleteTaskEntry(task3_2);
        eb2.addActivity(activity1);

        eb3.addCompleteTaskEntry(task4);
        eb3.addCompleteTaskEntry(task5);
        eb3.addActivity(activity2);
        eb3.addActivity(activity3);
    }

    @Test
    void testConstructorOne() {
        assertEquals(0, testDiary.getToDoList().size());
        assertEquals(0, testDiary.getEntryBatches().size());
    }

    @Test
    void testConstructorTwo() {
        List<EntryBatch> entryBatches = new ArrayList<>();
        entryBatches.add(eb1);
        entryBatches.add(eb2);
        List<Task> todoList = new LinkedList<>();
        todoList.add(task4);
        todoList.add(task5);
        List<Task> completedTasks = new ArrayList<>();
        completedTasks.add(task1);
        completedTasks.add(task2);
        completedTasks.add(task3_1);
        completedTasks.add(task3_2);
        Diary testDiaryTwo = new Diary(entryBatches, todoList, completedTasks);
        assertEquals(todoList, testDiaryTwo.getToDoList());
        assertEquals(entryBatches, testDiaryTwo.getEntryBatches());
        assertEquals(completedTasks, testDiaryTwo.getCompletedTasks());
    }

    @Test
    void testAddEntryBatch() {
        testDiary.addEntryBatch(eb1);
        List<EntryBatch> entryBatches = testDiary.getEntryBatches();
        assertEquals(1, entryBatches.size());
        assertTrue(entryBatches.contains(eb1));

        testDiary.addEntryBatch(eb2);
        testDiary.addEntryBatch(eb3);
        entryBatches = testDiary.getEntryBatches();
        assertEquals(3,entryBatches.size());
        assertEquals(eb3, entryBatches.get(0));
        assertEquals(eb2, entryBatches.get(1));
        assertEquals(eb1, entryBatches.get(2));
    }

    @Test
    void testAddTask() {
        testDiary.addTask(task1);
        List<Task> taskList = testDiary.getToDoList();
        assertEquals(1, taskList.size());
        assertTrue(taskList.contains(task1));

        testDiary.addTask(task2);
        testDiary.addTask(task3_1);
        taskList = testDiary.getToDoList();
        assertEquals(3, taskList.size());
        assertEquals(task1, taskList.get(0));
        assertEquals(task2, taskList.get(1));
        assertEquals(task3_1, taskList.get(2));
    }

    @Test
    void testToDoOrdering1() {
        addTasksInOrder();
        assertTaskOrder(true);
    }

    private void addTasksInOrder() {
        testDiary.addTask(task1);
        testDiary.addTask(task2);
        testDiary.addTask(task3_1);
        testDiary.addTask(task3_2);
        testDiary.addTask(task4);
        testDiary.addTask(task5);
    }

    @Test
    void testToDoOrdering2() {
        testDiary.addTask(task2);
        testDiary.addTask(task5);
        testDiary.addTask(task3_2);
        testDiary.addTask(task1);
        testDiary.addTask(task4);
        testDiary.addTask(task3_1);
        assertTaskOrder(false);
    }

    @Test
    void testToDoOrdering3() {
        testDiary.addTask(task3_1);
        testDiary.addTask(task5);
        testDiary.addTask(task2);
        testDiary.addTask(task4);
        testDiary.addTask(task1);
        testDiary.addTask(task3_2);
        assertTaskOrder(true);
    }

    @Test
    void testToDoOrdering4() {
        testDiary.addTask(task5);
        testDiary.addTask(task4);
        testDiary.addTask(task3_2);
        testDiary.addTask(task3_1);
        testDiary.addTask(task2);
        testDiary.addTask(task1);
        assertTaskOrder(false);
    }

    /**
     * Asserts the ordering of tasks in the list.
     *
     * @param oneFirst true if task3_1 was added first, false otherwise.
     */
    private void assertTaskOrder(boolean oneFirst) {
        List<Task> taskList = testDiary.getToDoList();
        assertEquals(6, taskList.size());
        assertEquals(task1, taskList.get(0));
        assertEquals(task2, taskList.get(1));
        if (oneFirst) {
            assertEquals(task3_1, taskList.get(2));
            assertEquals(task3_2, taskList.get(3));
        } else {
            assertEquals(task3_1, taskList.get(3));
            assertEquals(task3_2, taskList.get(2));
        }
        assertEquals(task4, taskList.get(4));
        assertEquals(task5, taskList.get(5));
    }

    @Test
    void testRemovalOfCompletedTasks() {
        addTasksInOrder();
        testDiary.addEntryBatch(eb1);
        List<Task> todoList = testDiary.getToDoList();
        assertTrue(todoList.contains(task3_1));
        assertTrue(todoList.contains(task3_2));
        assertTrue(todoList.contains(task4));
        assertTrue(todoList.contains(task5));
        assertFalse(todoList.contains(task1));
        assertFalse(todoList.contains(task2));
        List<Task> completedList = testDiary.getCompletedTasks();
        assertFalse(completedList.contains(task3_1));
        assertFalse(completedList.contains(task3_2));
        assertFalse(completedList.contains(task4));
        assertFalse(completedList.contains(task5));
        assertTrue(completedList.contains(task1));
        assertTrue(completedList.contains(task2));
    }

    @Test
    void testClearCompletedTasks() {
        addTasksInOrder();
        testDiary.addEntryBatch(eb1);
        testDiary.clearCompletedTasks();
        assertEquals(0, testDiary.getCompletedTasks().size());
    }
}
