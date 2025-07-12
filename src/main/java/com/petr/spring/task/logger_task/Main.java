package com.petr.spring.task.logger_task;

import com.petr.spring.task.logger_task.bean.ConsoleLogger;
import com.petr.spring.task.logger_task.bean.FileLogger;
import com.petr.spring.task.logger_task.config.ApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        FileLogger fileLogger = context.getBean(FileLogger.class);
        fileLogger.log("This is test File logger");

        ConsoleLogger consoleLogger = context.getBean(ConsoleLogger.class);
        consoleLogger.log("This is Console logger");

    }
}
