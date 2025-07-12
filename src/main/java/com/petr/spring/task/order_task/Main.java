package com.petr.spring.task.order_task;

import com.petr.spring.task.order_task.bean.OrderSerivce;
import com.petr.spring.task.order_task.config.ApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.random.RandomGenerator;

public class Main {
    public static void main(String[] args) {
        RandomGenerator randomGeneratorId =  RandomGenerator.getDefault();
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        OrderSerivce orderSerivce = context.getBean(OrderSerivce.class);
        orderSerivce.processOrder(randomGeneratorId.nextInt(0,100));

    }
}
