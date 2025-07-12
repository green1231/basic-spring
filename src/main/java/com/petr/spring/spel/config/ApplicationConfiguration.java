package com.petr.spring.spel.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.petr.spring.spel")
@PropertySource("classpath:application-spel.properties")
public class ApplicationConfiguration {

}
