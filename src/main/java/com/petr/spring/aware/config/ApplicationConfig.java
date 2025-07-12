package com.petr.spring.aware.config;

import com.petr.spring.aware.bean.Chef;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.petr.spring.aware")
public class ApplicationConfig {

    @Bean
    public Chef restaurantChef(){
        return  new Chef();
    }
}
