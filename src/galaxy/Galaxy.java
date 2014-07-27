package galaxy;

import database.CreateUser;
import database.DatabaseConnection;
import dto.User;

public class Galaxy {

	public static void main(String[] args) {
		DatabaseConnection dc = DatabaseConnection.getInstance();
		
		CreateUser createUserTread = new CreateUser();
		createUserTread.run();
		
		User user = dc.selectUser(2);

		System.out.println(user.getUID());
	}
}
