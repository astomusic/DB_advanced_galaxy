package database;

import dto.User;


public class CreateUser extends Thread{
	private final int userNumber = 10; // 생성할 유저수
	
	public void run() {
		System.out.println("CreateUser Thread Start");
		DatabaseConnection dc = DatabaseConnection.getInstance();
		
		int last;
		User user;
		
		for(int i=0 ; i<userNumber ; i++) {
			dc.createUser();
			last = dc.getLast();
			user = dc.selectUser(last);
			
			
		}
		
	}

}
