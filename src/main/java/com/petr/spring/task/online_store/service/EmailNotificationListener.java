package com.petr.spring.task.online_store.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationListener {

    @EventListener
    public void onOrderPlaced(OrderPlacedEvent event) {
        System.out.println("[EMAIL] Sending mail: "
                + "User=" + event.getUser().getUsername()
                + ", Email=" + event.getUser().getEmail()
                + ", Ordered: " + event.getOrder().getProduct()
                + " for " + event.getOrder().getPrice());
    }
}



