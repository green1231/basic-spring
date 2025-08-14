package com.petr.spring.application.model;

import java.io.Serial;
import java.io.Serializable;

public class Task implements Serializable {
    @Serial
    private static long serialVersionUID = 1L;

    private String id;
    private String description;
    private boolean completed =false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
