package com.petr.spring.proxy.bean;

public class Customer {
    private String name;

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    public void palaceOrder(IWaiter waiter){
        waiter.serve(name);
    }

    public String getName() {
        return name;
    }
}
