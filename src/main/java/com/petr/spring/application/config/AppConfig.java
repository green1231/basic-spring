package com.petr.spring.application.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.petr.spring.application")
@PropertySource("classpath:task-application.properties")
public class AppConfig {
}
