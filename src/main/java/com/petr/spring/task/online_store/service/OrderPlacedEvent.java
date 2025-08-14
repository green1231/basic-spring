package com.petr.spring.task.online_store.service;

import com.petr.spring.task.online_store.model.Order;
import com.petr.spring.task.online_store.model.User;
import org.springframework.context.ApplicationEvent;

public class OrderPlacedEvent extends ApplicationEvent {
    private final User user;
    private final Order order;

    public OrderPlacedEvent(Object source, User user, Order order) {
        super(source);
        this.user = user;
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public Order getOrder() {
        return order;
    }
}



