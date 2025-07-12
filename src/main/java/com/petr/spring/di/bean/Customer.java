package com.petr.spring.di.bean;

import org.springframework.stereotype.Component;

@Component
public class Customer {
    private final Waiter waiter;

    public Customer(Waiter waiter) {
        this.waiter = waiter;
    }
    public void makeOrder(String order){
        System.out.println("Customer make order: "+order );
        waiter.takeOrder(order);
    }
}
