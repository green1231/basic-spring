package com.petr.spring.task.online_store.cli;

import com.petr.spring.task.online_store.model.Role;
import com.petr.spring.task.online_store.model.User;
import com.petr.spring.task.online_store.service.OrderOperations;
import com.petr.spring.task.online_store.service.SessionService;
import com.petr.spring.task.online_store.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class ConsoleMenu implements Runnable {

    private final UserService userService;
    private final SessionService sessionService;
    private final OrderOperations orderService;

    private final Scanner scanner = new Scanner(System.in);

    public ConsoleMenu(UserService userService, SessionService sessionService, OrderOperations orderService) {
        this.userService = userService;
        this.sessionService = sessionService;
        this.orderService = orderService;
    }

    @Override
    public void run() {
        System.out.println("Welcome to the online store.");
        boolean running = true;
        while (running) {
            printHeader();
            printMenu();
            String input = scanner.nextLine().trim();
            try {
                switch (input) {
                    case "1":
                        handleLogin();
                        break;
                    case "2":
                        handleCreateUser(false);
                        break;
                    case "3":
                        handleCreateUser(true);
                        break;
                    case "4":
                        handleGrantBuyer();
                        break;
                    case "5":
                        handleListUsers();
                        break;
                    case "6":
                        handlePlaceOrder();
                        break;
                    case "7":
                        sessionService.logout();
                        System.out.println("You got out of the system.");
                        break;
                    case "0":
                        running = false;
                        System.out.println("Completion of work...");
                        break;
                    default:
                        System.out.println("Unknown number");
                }
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println("[error] " + e.getMessage());
            } catch (Exception e) {
                System.out.println("[error] An unforeseen mistake: " + e.getMessage());
            }
        }
    }

    private void printHeader() {
        Optional<User> current = sessionService.getCurrentUser();
        if (current.isPresent()) {
            User u = current.get();
            System.out.println("Current user: " + u.getUsername() + " Roles =" + u.getRoles());
        } else {
            System.out.println("Current user: <not authorized>");
        }
    }

    private void printMenu() {
        System.out.println("Menu:");
        System.out.println("1) Enter");
        System.out.println("2) Create user (without a role)");
        System.out.println("3) Create user (with the role of user_buyer)");
        System.out.println("4) To give the role of user_buyer to the existing user");
        System.out.println("5) List of users");
        System.out.println("6) Place an order");
        System.out.println("7) Leave the account");
        System.out.println("0) Exit from the program");
        System.out.print("OPTION: ");
    }

    private void handleLogin() {
        System.out.print("Login: ");
        String username = scanner.nextLine().trim();
        System.out.print("PASSWORD: ");
        String password = scanner.nextLine().trim();
        if (userService.checkCredentials(username, password)) {
            userService.findByUsername(username).ifPresent(sessionService::login);
            System.out.println("Successful entrance.");
        } else {
            System.out.println("Inappropriate accounting data.");
        }
    }

    private void handleCreateUser(boolean asBuyer) {
        ensureAdmin();
        System.out.print("Login of the new user: ");
        String username = scanner.nextLine().trim();
        System.out.print("PASSWORD: ");
        String password = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        userService.createUser(username, password, email, asBuyer);
        System.out.println("The user is created.");
    }

    private void handleGrantBuyer() {
        ensureAdmin();
        System.out.print("User login: ");
        String username = scanner.nextLine().trim();
        userService.grantBuyerRole(username);
        System.out.println("The role of user_buyer is issued to the user " + username);
    }

    private void handleListUsers() {
        ensureAdmin();
        List<User> users = userService.listUsers();
        System.out.println("Total users: " + users.size());
        for (User u : users) {
            System.out.println("- " + u.getUsername() + " | " + u.getEmail() + " | role: " + u.getRoles() + " | order: " + u.getOrders().size());
        }
    }

    private void handlePlaceOrder() {
        System.out.print("PRODUCT: ");
        String product = scanner.nextLine().trim();
        System.out.print("Price: ");
        double price;
        try {
            price = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("[error] Invalid number. Try again.");
            return;
        }
        try {
            orderService.placeOrder(product, price);
            System.out.println("The order is placed.");
        } catch (IllegalStateException e) {
            System.out.println("[error] " + e.getMessage());
        }
    }

    private void ensureAdmin() {
        User current = sessionService.getCurrentUser().orElseThrow(() -> new IllegalStateException("Entrance is required"));
        if (!current.getRoles().contains(Role.ADMIN)) {
            throw new IllegalStateException("Only the administrator can perform this action");
        }
    }
}


