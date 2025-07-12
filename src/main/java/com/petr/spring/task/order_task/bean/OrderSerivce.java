package com.petr.spring.task.order_task.bean;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderSerivce {

    private final ObjectFactory<OrderContext> orderContext;


    public OrderSerivce(ObjectFactory<OrderContext> orderContext) {
        this.orderContext = orderContext;
    }

    public void processOrder(int orderId) {
        OrderContext context = orderContext.getObject();
        context.setOrderId(String.valueOf(orderId));
        System.out.println("Order processing: orderId = " + orderId);
    }
}
