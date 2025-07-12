package com.petr.spring.di.config;

import com.petr.spring.di.bean.Kitchen;
import com.petr.spring.di.bean.Waiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.petr.spring.di")
public class CafeConfiguration {

    @Bean
    public Waiter waiter(Kitchen kitchen) {
        return new Waiter(kitchen);
    }
}
