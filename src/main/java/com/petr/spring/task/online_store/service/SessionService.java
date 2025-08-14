package com.petr.spring.task.online_store.service;

import com.petr.spring.task.online_store.model.Role;
import com.petr.spring.task.online_store.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionService {

    private User currentUser;

    public Optional<User> getCurrentUser() {
        return Optional.ofNullable(currentUser);
    }

    public void login(User user) {
        this.currentUser = user;
    }

    public void logout() {
        this.currentUser = null;
    }

    public boolean currentUserHasRole(Role role) {
        if (currentUser == null) {
            return false;
        }
        return currentUser.getRoles().contains(role);
    }
}



