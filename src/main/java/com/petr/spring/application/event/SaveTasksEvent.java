package com.petr.spring.application.event;

import org.springframework.context.ApplicationEvent;

public class SaveTasksEvent extends ApplicationEvent {
    public SaveTasksEvent(Object source) {
        super(source);
    }
}
