package com.petr.spring.task.logger_task.bean;


import org.springframework.stereotype.Component;

@Component
public class ConsoleLogger implements CustomLogger {
    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
