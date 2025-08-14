package com.petr.spring.event.bean;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

@Component
public class BasicHttpServer {
    private final Restaurant restaurant;

    public BasicHttpServer(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @PostConstruct
    public void startServer() {
        start();
    }

    private void start() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/order", processOrderHandler());
            server.start();

            System.out.println("Server is running on 8080 port...");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    private HttpHandler processOrderHandler() {
        return exchange -> {
            if ("POST".equals(exchange.getRequestMethod())) {
                InputStream requestBody = exchange.getRequestBody();
                byte[] bodyByte = requestBody.readAllBytes();
                String order = new String(bodyByte);
                System.out.println("Received order from request: "+ order);

                restaurant.placeOrder(order);
                String response = "Order received successfully";
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();

            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        };
    }
}
