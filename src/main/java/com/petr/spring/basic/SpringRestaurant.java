package com.petr.spring.basic;

import com.petr.spring.basic.config.RestaurantConfiguration;
import com.petr.spring.basic.staff.Barista;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringRestaurant {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RestaurantConfiguration.class);
        Barista barista = context.getBean("barista",Barista.class);
        barista.makeCoffee();

    }
}
