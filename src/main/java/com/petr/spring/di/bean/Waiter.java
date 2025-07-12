package com.petr.spring.di.bean;


public class Waiter {

    private Kitchen kitchen;

    public Waiter(Kitchen kitchen) {
        this.kitchen = kitchen;
    }

    public void takeOrder(String order){
        System.out.println("Waiter takes order: "+order);
        kitchen.cook(order);
    }
}
