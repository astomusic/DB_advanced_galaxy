package galaxy;

import database.CreateUser;

public class Galaxy {

	public static void main(String[] args) {
		CreateUser createUserTread = new CreateUser();
		createUserTread.run();
	}
}
