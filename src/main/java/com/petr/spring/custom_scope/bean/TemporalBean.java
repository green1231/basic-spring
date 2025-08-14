package com.petr.spring.custom_scope.bean;

import java.util.UUID;

public class TemporalBean {

    private final String id = UUID.randomUUID().toString();

    public void printId(){
        System.out.println("TemporalBean id: "+id);
    }
}
