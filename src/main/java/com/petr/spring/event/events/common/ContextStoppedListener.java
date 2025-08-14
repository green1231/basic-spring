package com.petr.spring.event.events.common;

import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ContextStoppedListener {

   @EventListener(ContextStoppedEvent.class)
   public void listen(ContextStoppedEvent event){
      System.out.println("Context stopped. Source: "+ event.getSource());

   }
}
