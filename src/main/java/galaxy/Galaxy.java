package galaxy;

import database.AttackGalaxy;
import database.CreateUser;

public class Galaxy {

	public static void main(String[] args) {
		CreateUser createUserTread = new CreateUser();
		createUserTread.run();
		
		AttackGalaxy attackGalaxyThread = new AttackGalaxy();
		attackGalaxyThread.run();
	}
}
