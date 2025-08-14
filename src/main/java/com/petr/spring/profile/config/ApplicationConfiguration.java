package com.petr.spring.profile.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.petr.spring.profile")
@PropertySource("classpath:default-profile-application.properties")
public class ApplicationConfiguration {

    @Configuration
    @Profile("latte")
    @PropertySource("classpath:latte-profile-application.properties")
    static class LatteConfig{

    }

    @Configuration
    @Profile("espresso")
    @PropertySource("classpath:espresso-profile-application.properties")
    static class EspressoConfig{

    }
}
