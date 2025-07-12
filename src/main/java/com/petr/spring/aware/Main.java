package com.petr.spring.aware;

import com.petr.spring.aware.bean.Menu;
import com.petr.spring.aware.bean.Waiter;
import com.petr.spring.aware.config.ApplicationConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        Waiter waiter = context.getBean(Waiter.class);
        waiter.takeOrder("Salate");
        waiter.greetCustomer();
        context.getBean(Menu.class).printMenu();

    }
}
