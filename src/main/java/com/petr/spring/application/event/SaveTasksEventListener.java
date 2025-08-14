package com.petr.spring.application.event;

import com.petr.spring.application.service.TaskService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SaveTasksEventListener {
    @Value("${app.tasks-file-path}")
    private String filePath;

    private final TaskService taskService;


    public SaveTasksEventListener(TaskService taskService) {
        this.taskService = taskService;
    }

    @EventListener(SaveTasksEvent.class)
    public void onEvent(SaveTasksEvent event){
        System.out.println("Save tasks...");
        taskService.saveTaskFile(filePath);
    }
}
