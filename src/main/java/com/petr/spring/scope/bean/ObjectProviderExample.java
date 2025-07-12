package com.petr.spring.scope.bean;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Component
public class ObjectProviderExample {
    private final ObjectProvider<Waiter> waiterObjectProvider;

    private final ObjectProvider<HeadChef> headChefObjectProvider;

    public ObjectProviderExample(ObjectProvider<Waiter> waiterObjectProvider, ObjectProvider<HeadChef> headChefObjectProvider) {
        this.waiterObjectProvider = waiterObjectProvider;
        this.headChefObjectProvider = headChefObjectProvider;
    }
    public void getAndCompareHeadChef(){
        HeadChef first = headChefObjectProvider.getObject();
        HeadChef second =headChefObjectProvider.getObject();
        System.out.println("HeadChef is equals: " +(first==second));

    }

    public void getAndCompareWaiter(){
        Waiter first = waiterObjectProvider.getObject();
        Waiter second = waiterObjectProvider.getObject();
        System.out.println("Waiter is equals: "+(first==second));
     }
}
