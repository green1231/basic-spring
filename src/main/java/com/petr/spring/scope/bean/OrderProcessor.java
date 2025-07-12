package com.petr.spring.scope.bean;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessor {

    private final ObjectProvider<Waiter> objectProvider;
    private final HeadChef headChef;

    public OrderProcessor(ObjectProvider<Waiter> objectProvider, HeadChef headChef) {
        this.objectProvider = objectProvider;
        this.headChef = headChef;
    }
    public void processorOrder(String order, int table){
        Waiter waiter = objectProvider.getObject();
        waiter.takeOrder(order,table);
        headChef.makeOrder(waiter);

    }
}
