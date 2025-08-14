package com.petr.spring.task.measure_bean;

import com.petr.spring.task.measure_bean.config.ApplicationConfig;
import com.petr.spring.task.measure_bean.service.TimedService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        TimedService timedService = context.getBean(TimedService.class);

        try {
            timedService.methodWith1SecondDelay();
            timedService.methodWith3SecondsDelay();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        timedService.methodWithoutDelay();


    }
}
