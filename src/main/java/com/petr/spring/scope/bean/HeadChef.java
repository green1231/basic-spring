package com.petr.spring.scope.bean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class HeadChef {
    private String name;

    public void makeOrder(Waiter waiter){

        System.out.println("HeadChef start cooking: Waiter is "+ waiter);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeadChef{" +
                "name='" + name + '\'' +
                '}';
    }
}
