package galaxy;

import database.CreateUser;
import database.DatabaseConnection;

public class Galaxy {

	public static void main(String[] args) {
		DatabaseConnection dc = DatabaseConnection.getInstance();
		
		CreateUser createUserTread = new CreateUser();
		createUserTread.run();
	}
}
