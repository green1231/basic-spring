package com.petr.spring.profile.bean;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Component
//@Primary
@Profile("latte")
public class Latte implements Coffee{
    @Override
    public String brew() {
        return "Latte";
    }
}
