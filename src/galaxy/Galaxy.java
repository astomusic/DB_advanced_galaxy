package galaxy;

import database.DatabaseConnection;

public class Galaxy {

	public static void main(String[] args) {
		DatabaseConnection dc = new DatabaseConnection();
		dc.createUser();
	}
}
