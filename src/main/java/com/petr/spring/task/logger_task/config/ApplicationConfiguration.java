package com.petr.spring.task.logger_task.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.petr.spring.task.logger_task")
@PropertySource("application-logger.properties")
public class ApplicationConfiguration {

}
