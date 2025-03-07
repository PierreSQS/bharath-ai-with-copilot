package com.bharath.copilot;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskManagerTest {
    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new TaskManager();
    }

    @Test
    void testAddTask() {
        taskManager.addTask("Test task");
        assertEquals(1, taskManager.listTasks().size());
        assertEquals("Test task", taskManager.listTasks().get(0).getDescription());
    }

    @Test
    void testListTasks() {
        taskManager.addTask("Task 1");
        taskManager.addTask("Task 2");
        assertEquals(2,taskManager.listTasks().size());
        assertEquals("Task 1", taskManager.listTasks().get(0).getDescription());
        assertEquals("Task 2", taskManager.listTasks().get(1).getDescription());
        
    }

    @Test
    void testMarkTaskAsDone() {
        taskManager.addTask("Task to be done");
        taskManager.markTaskAsDone("Task to be done");
        assertTrue(taskManager.listTasks().get(0).isDone());
    }

    @Test
    void testMarkTaskAsDoneWithNonexistentTask() {
        taskManager.markTaskAsDone("Nonexistent Task");
        assertEquals(0, taskManager.listTasks().size());
    }

    @Test
    void testRemoveTask() {
        taskManager.addTask("Task to be removed");
        taskManager.removeTask("Task to be removed");
        assertEquals(0, taskManager.listTasks().size());
    }

}
    