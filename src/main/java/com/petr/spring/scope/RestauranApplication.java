package com.petr.spring.scope;

import com.petr.spring.scope.bean.OrderProcessor;
import com.petr.spring.scope.config.RestaurantCinfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RestauranApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RestaurantCinfiguration.class);
        OrderProcessor processor = context.getBean(OrderProcessor.class);
        processor.processorOrder("Coffe",9);
    }
}
