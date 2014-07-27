package web;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
	public static void main(String[] args) throws IOException {
		ServerSocket server =  new ServerSocket(5000);
		System.out.println("WebServer Start");
				
		Socket connection = null;
		while(true) {
			connection = server.accept();
		}

	}
}
