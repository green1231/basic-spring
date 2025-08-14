package com.petr.spring.task.online_store;

import com.petr.spring.task.online_store.config.AppConfig;
import com.petr.spring.task.online_store.cli.ConsoleMenu;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            ConsoleMenu menu = context.getBean(ConsoleMenu.class);
            menu.run();
        }
    }
}
