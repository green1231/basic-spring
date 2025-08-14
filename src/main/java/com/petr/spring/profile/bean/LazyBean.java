package com.petr.spring.profile.bean;


import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Lazy
public class LazyBean {

    private final Holder holder;

    public LazyBean(Holder holder) {
        this.holder = holder;
    }

    @PostConstruct
    public void construct() {
        System.out.println("Lazy bean construct");
    }

    public String getInfo() {
        return "I'm lazy bean";
    }

    public void fireHolderBean(){
        System.out.println("From Lazy bean: "+ holder.getInfo());
    }
}
