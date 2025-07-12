package com.petr.spring.postprocessor.bean;

import org.springframework.stereotype.Component;

@Component
public class Waiter {

    private boolean onDuty;

    public void takeOrder(){
        if(onDuty){
            System.out.println("I can take order");
        }
        else
        {
            System.out.println(" I'm not on duty");
        }
    }

    public void setOnDuty(boolean onDuty) {
        this.onDuty = onDuty;
    }

    public boolean isOnDuty() {
        return onDuty;
    }
}
