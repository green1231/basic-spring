package com.petr.spring.task.custombean_task;

import com.petr.spring.task.custombean_task.bean.MyBean;
import com.petr.spring.task.custombean_task.config.ApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        MyBean myBean = context.getBean(MyBean.class);
        System.out.println("Random number: " + myBean.getRandomNumber());
    }
}
