package web;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class WebServer{

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(5000), 0);
        server.createContext("/", new Handler());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("WebServer Start");
    }
}