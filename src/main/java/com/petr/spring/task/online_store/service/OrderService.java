package com.petr.spring.task.online_store.service;

import com.petr.spring.task.online_store.bean.RequireRole;
import com.petr.spring.task.online_store.model.Order;
import com.petr.spring.task.online_store.model.Role;
import com.petr.spring.task.online_store.model.User;
import com.petr.spring.task.online_store.repository.StoreRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements OrderOperations {

    private final StoreRepository storeRepository;
    private final SessionService sessionService;
    private final ApplicationEventPublisher eventPublisher;

    public OrderService(StoreRepository storeRepository,
                        SessionService sessionService,
                        ApplicationEventPublisher eventPublisher) {
        this.storeRepository = storeRepository;
        this.sessionService = sessionService;
        this.eventPublisher = eventPublisher;
    }

    @RequireRole(Role.USER_BUYER)
    @Override
    public synchronized void placeOrder(String product, double price) {
        User user = sessionService.getCurrentUser().orElseThrow(() -> new IllegalStateException("Не выполнен вход"));
        Order order = new Order(product, price);
        List<User> users = storeRepository.loadUsers();
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                u.getOrders().add(order);
                break;
            }
        }
        storeRepository.saveUsers(users);
        eventPublisher.publishEvent(new OrderPlacedEvent(this, user, order));
    }
}


