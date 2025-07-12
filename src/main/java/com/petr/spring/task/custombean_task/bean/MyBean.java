package com.petr.spring.task.custombean_task.bean;

import org.springframework.stereotype.Component;

@Component
public class MyBean {
    @RandomNumber
    private int randomNumber;

    public int getRandomNumber() {
        return randomNumber;
    }
} 