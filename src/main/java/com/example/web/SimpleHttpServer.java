package com.example.web;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleHttpServer {

    public void start() throws IOException {
        int port = 8000; // Server runs on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/", this::handleHome);
        server.createContext("/health", this::handleHealth);

        server.setExecutor(null);
        server.start();

        System.out.println("Server started on port " + port);
    }

    private void handleHome(HttpExchange exchange) throws IOException {
        String response = "Welcome to Java Web App! ✔ Jenkins Integration Working!";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void handleHealth(HttpExchange exchange) throws IOException {
        String response = "{\"status\":\"UP\"}";
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, response.length());
        exchange.getResponseBody().write(response.getBytes());
        exchange.getResponseBody().close();
    }
}
