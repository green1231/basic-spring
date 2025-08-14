package com.petr.spring.application;

import com.petr.spring.application.config.AppConfig;
import com.petr.spring.application.event.SaveTasksEvent;
import com.petr.spring.application.service.TaskService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        new Application().start();
    }
    public void start(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        TaskService taskService = context.getBean(TaskService.class);
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("1. Add Task");
            System.out.println("2. Complete Task");
            System.out.println("3. List Tasks");
            System.out.println("4. Save Tasks");
            System.out.println("5. Exit");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option){
                case 1:
                    System.out.print("Enter task description");
                    String description = scanner.nextLine();
                    taskService.addTask(description);
                    break;
                case 2:
                    System.out.print("Enter task ID to complete");
                    String taskId = scanner.nextLine();
                    taskService.completeTask(taskId);
                    break;
                case 3:
                    System.out.println("Tasks: ");
                    taskService.getAllTasks().forEach(task -> {
                        System.out.println(task.getId()+ ": "+task.getDescription()+"( Completed: "+ task.getCompleted()+")");
                    });
                    break;
                case 4:
                    context.publishEvent(new SaveTasksEvent(this));
                    break;
                case 5:
                    context.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");


            }
        }
    }
}
