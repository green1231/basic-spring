package com.petr.spring.event.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

@Component
@ComponentScan("com.petr.spring.event")
public class ApplicationConfig {

    @Bean
    public BlockingQueue<String> orderQueue(){
        return new LinkedBlockingDeque<>();
    }
}
