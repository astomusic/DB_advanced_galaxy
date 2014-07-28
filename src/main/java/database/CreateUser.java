package database;

import dto.User2DB;


public class CreateUser extends Thread{
	private final int userNumber = 1; // 생성할 유저수
	
	public void run() {
		System.out.println("CreateUser Thread Start");
		DatabaseConnection dc = DatabaseConnection.getInstance();
		
		int last;
		User2DB user;
		
		for(int i=0 ; i<userNumber ; i++) {
			dc.createGlobalUser();
			last = dc.getLast();
			user = dc.selectGlobalUser(last);
			
			dc.insertUser(user);			
		}
	}
}
