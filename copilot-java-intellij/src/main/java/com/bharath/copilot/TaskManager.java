package com.bharath.copilot;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The TaskManager class is responsible for managing a list of tasks.
 * It provides methods to add a task, list all tasks, mark a task as done, and remove a task.
 */
public class TaskManager {
    private final List<Task> tasks = new CopyOnWriteArrayList<>();

    /**
     * The Task class represents a task with a description and a done flag.
     * It uses Lombok annotations to generate boilerplate code.
     */
    @Data
    @Builder
    @ToString
    public static class Task {
        private String description;
        private boolean done;

        /**
         * Marks the task as done by setting the done flag to true.
         */
        public void markAsDone() {
            this.done = true;
        }
    }

    /**
     * Adds a new task with the given description to the task list.
     *
     * @param description the description of the task to be added
     */
    public void addTask(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Task description cannot be null or empty");
        }
        tasks.add(Task.builder()
                .description(description)
                .done(false)
                .build());
    }

    /**
     * Returns a list of all tasks.
     *
     * @return a list of all tasks
     */
    public List<Task> listTasks() {
        return Collections.unmodifiableList(tasks);
    }

    /**
     * Marks the task with the given description as done.
     *
     * @param description the description of the task to be marked as done
     */
    public void markTaskAsDone(String description) {
        findTaskByDescription(description).ifPresent(Task::markAsDone);
    }

    /**
     * Removes the task with the given description from the task list.
     *
     * @param description the description of the task to be removed
     */
    public void removeTask(String description) {
        tasks.removeIf(task -> task.getDescription().equals(description));
    }

    /**
     * Finds a task by its description.
     *
     * @param description the description of the task to find
     * @return an Optional containing the found task, or an empty Optional if no task was found
     */
    private Optional<Task> findTaskByDescription(String description) {
        return tasks.stream()
                .filter(task -> task.getDescription().equals(description))
                .findFirst();
    }
}