package com.petr.spring.application.event;

import org.springframework.context.ApplicationEvent;

public class TaskCompletedEvent extends ApplicationEvent {
    private final String taskId;
    private final boolean completed;

    public TaskCompletedEvent(Object source, String taskId, boolean completed) {
        super(source);
        this.taskId = taskId;
        this.completed = completed;
    }

    public String getTaskId() {
        return taskId;
    }

    public boolean isCompleted() {
        return completed;
    }
}
