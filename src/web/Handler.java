package web;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import database.DatabaseConnection;

public class Handler implements HttpHandler {
	public void handle(HttpExchange t) throws IOException {
		String response = javaIntoHtml();
		t.sendResponseHeaders(200, response.length());
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

	private String readFileAsString(String filePath) throws IOException {
		StringBuffer fileData = new StringBuffer();
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
		}
		reader.close();
		return fileData.toString();
	}
	
	private String javaIntoHtml() throws IOException {
		DatabaseConnection dc = DatabaseConnection.getInstance();
		String last = String.valueOf(dc.getLast());
		String result = readFileAsString("/Users/astomusic/Documents/workspace/DB_advanced_galaxy/resource/main.html");
		result = result.replaceAll("\\{user\\}", last);
		System.out.println(result);
		return result;
	}

}
