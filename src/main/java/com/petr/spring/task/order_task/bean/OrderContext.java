package com.petr.spring.task.order_task.bean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OrderContext {
    String orderId;

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}
