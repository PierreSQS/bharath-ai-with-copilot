package com.bharath.copilot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {

    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new TaskManager();
    }

    @Test
    void addTask_shouldAddTaskToList() {
        taskManager.addTask("New Task");
        List<TaskManager.Task> tasks = taskManager.listTasks();
        assertEquals(1, tasks.size());
        assertEquals("New Task", tasks.getFirst().getDescription());
        assertFalse(tasks.getFirst().isDone());
    }

    @Test
    void listTasks_shouldReturnAllTasks() {
        taskManager.addTask("Task 1");
        taskManager.addTask("Task 2");
        List<TaskManager.Task> tasks = taskManager.listTasks();
        assertEquals(2, tasks.size());
    }

    @Test
    void markTaskAsDone_shouldMarkTaskAsDone() {
        taskManager.addTask("Task to be done");
        taskManager.markTaskAsDone("Task to be done");
        List<TaskManager.Task> tasks = taskManager.listTasks();
        assertTrue(tasks.getFirst().isDone());
    }

    @Test
    void markTaskAsDone_shouldNotMarkNonExistentTask() {
        taskManager.addTask("Existing Task");
        taskManager.markTaskAsDone("Non-Existent Task");
        List<TaskManager.Task> tasks = taskManager.listTasks();
        assertFalse(tasks.getFirst().isDone());
    }

    @Test
    void addTask_shouldHandleEmptyDescription() {
        assertThrows(IllegalArgumentException.class, () -> taskManager.addTask(""));
    }

    @Test
    void addTask_shouldHandleNullDescription() {
        assertThrows(IllegalArgumentException.class, () -> taskManager.addTask(null));
    }

    @Test
    void removeTask() {
        taskManager.addTask("Task to be removed");
        taskManager.removeTask("Task to be removed");
        List<TaskManager.Task> tasks = taskManager.listTasks();
        assertEquals(0, tasks.size());
    }
}
