package com.bharath.copilot;

import java.util.ArrayList;
import java.util.List;

/**
 * The Task class represents a task with a description and a status indicating whether it is done.
 */
class Task {
    /**
     * The description of the task.
     */
    private final String description;

    /**
     * The status of the task, indicating whether it is done.
     */
    private boolean done;

    /**
     * Constructs a new Task with the specified description.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the task is done.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.done = true;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", done=" + done +
                '}';
    }
}

/**
 * The TaskManager class manages a list of tasks.
 */
public class TaskManager {

    /**
     * The list of tasks.
     */
    private final List<Task> tasks;

    /**
     * Constructs a new TaskManager.
     */
    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a new task with the specified description to the list of tasks.
     *
     * @param description the description of the task to be added
     */
    public void addTask(String description) {
        Task task = new Task(description);
        tasks.add(task);
    }

    /**
     * Returns the list of tasks.
     *
     * @return the list of tasks
     */
    public List<Task> listTasks() {
        return tasks;
    }

    /**
     * Marks the task with the specified description as done.
     *
     * @param description the description of the task to be marked as done
     */
    public void markTaskAsDone(String description) {
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                task.markAsDone();
                break;
            }
        }
    }

    public void removeTask(String description) {
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                tasks.remove(task);
                break;
            }
        }
    }
}