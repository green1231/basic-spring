package com.petr.spring.postprocessor.config;

import com.petr.spring.postprocessor.bean.Waiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.petr.spring.postprocessor")
public class ApplicationConfiguration {
    @Bean
    public Waiter waiter() {
        return new Waiter();
    }

}
