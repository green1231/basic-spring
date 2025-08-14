package com.petr.spring.application.service;

import com.petr.spring.application.event.TaskCompletedEvent;
import com.petr.spring.application.model.Task;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private final ApplicationEventPublisher publisher;
    private final List<Task> tasks = new ArrayList<>();

    public TaskService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void addTask(String description) {
        Task task = new Task();
        task.setId(UUID.randomUUID().toString());
        task.setDescription(description);
        task.setCompleted(false);
        tasks.add(task);
    }

    public List<Task> getAllTasks(){
        return tasks;

    }

    public void completeTask(String taskId) {
        var completedTask = tasks.stream()
                .filter(task -> task.getId().equals(taskId))
                .findFirst();
        completedTask.ifPresent(t -> t.setCompleted(true));
        publisher.publishEvent(new TaskCompletedEvent(
                this,
                taskId,
                completedTask.map(Task::getCompleted).orElse(false)
        ));
    }

    public void saveTaskFile(String filePath) {

        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(tasks);
            oos.flush();
        } catch (IOException e) {
            System.out.println("Exception trying to save task in file. " + e.getMessage());
        }
    }

    public void loadTasks(String filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            ObjectInputStream ois = new ObjectInputStream(fileInputStream);
            tasks.addAll((List<Task>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Exception trying to load file. " + e.getMessage());
        }

    }

}

