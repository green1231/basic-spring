package com.petr.spring.lifecycle.config;

import com.petr.spring.lifecycle.bean.IngredientFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.petr.spring.lifecycle")
public class LifecycleConfiguration {

//    @Bean(initMethod = "openShop", destroyMethod = "closeShop")
//    public CoffeeShop coffeeShop() {
//                return new CoffeeShop();
//    }

    @Bean
    public IngredientFactoryBean sugar(){
        return new IngredientFactoryBean("sugar");
    }

    @Bean
    public IngredientFactoryBean milk(){
        return new IngredientFactoryBean("milk");
    }
}
