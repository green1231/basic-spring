package com.petr.spring.application;


import com.petr.spring.application.service.TaskService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("load")
public class TaskLoader {
    private String filePath;

    private final TaskService taskService;

    public TaskLoader(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostConstruct
    public void loadTasks(){
        System.out.println("Load tasks");
        taskService.loadTasks(filePath);
    }
}
