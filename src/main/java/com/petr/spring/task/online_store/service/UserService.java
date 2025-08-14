package com.petr.spring.task.online_store.service;

import com.petr.spring.task.online_store.model.Role;
import com.petr.spring.task.online_store.model.User;
import com.petr.spring.task.online_store.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final StoreRepository storeRepository;
    private final String adminUsername;
    private final String adminPassword;
    private final String adminEmail;

    public UserService(StoreRepository storeRepository,
                       @Value("${admin.username}") String adminUsername,
                       @Value("${admin.password}") String adminPassword,
                       @Value("${admin.email}") String adminEmail) {
        this.storeRepository = storeRepository;
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.adminEmail = adminEmail;

        ensureAdminExists();
    }

    private void ensureAdminExists() {
        List<User> users = storeRepository.loadUsers();
        boolean exists = users.stream().anyMatch(u -> u.getUsername().equals(adminUsername));
        if (!exists) {
            User admin = new User(adminUsername, adminPassword, adminEmail);
            admin.getRoles().add(Role.ADMIN);
            users.add(admin);
            storeRepository.saveUsers(users);
        }
    }

    public synchronized User createUser(String username, String password, String email, boolean asBuyer) {
        List<User> users = storeRepository.loadUsers();
        users.stream().filter(u -> u.getUsername().equals(username)).findFirst().ifPresent(u -> {
            throw new IllegalArgumentException("Пользователь с таким логином уже существует");
        });
        User user = new User(username, password, email);
        if (asBuyer) {
            user.getRoles().add(Role.USER_BUYER);
        }
        users.add(user);
        storeRepository.saveUsers(users);
        return user;
    }

    public synchronized Optional<User> findByUsername(String username) {
        return storeRepository.loadUsers().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }

    public synchronized List<User> listUsers() {
        return new ArrayList<>(storeRepository.loadUsers());
    }

    public synchronized void grantBuyerRole(String username) {
        List<User> users = storeRepository.loadUsers();
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                if (!u.getRoles().contains(Role.USER_BUYER)) {
                    u.getRoles().add(Role.USER_BUYER);
                }
                storeRepository.saveUsers(users);
                return;
            }
        }
        throw new IllegalArgumentException("Пользователь не найден: " + username);
    }

    public boolean checkCredentials(String username, String password) {
        return findByUsername(username)
                .map(u -> u.getPassword().equals(password))
                .orElse(false);
    }
}



