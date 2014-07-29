package database;

import dto.User2DB;

public class AttackGalaxy extends Thread{
	private int attckNumber = 1;
	private final int numberOfGalaxy = 4;
	
	public void run() {
		System.out.println("AttackGalaxy Thread Start");
		DatabaseConnection dc = DatabaseConnection.getInstance();
		
		int last, ramdomUser, ramdomGalaxy, userGalaxy, attackPower;
		
		for(int i=0 ; i<attckNumber ; i++) {
			//유저를 하나 랜덤으로 뽑고 (1~getLast())
			last = dc.getLast();
			ramdomUser  = (int) (Math.random() * last) + 1;
			User2DB user2db = dc.selectGlobalUser(ramdomUser);
			
			//해당 유저가 있는 샤드에서 해당 유저를 선택
			//User user = dc.selectUser(user2db);
			
			//은하를 랜덤으로 하나뽑는다 (1~4) 자신의 은하제외
			ramdomGalaxy  = (int) Math.random() * numberOfGalaxy + 1;
			userGalaxy = user2db.getGID();
			while(ramdomGalaxy == userGalaxy) { 
				ramdomGalaxy  = (int) Math.random() * numberOfGalaxy + 1;
			}
			
			//해당 유저의 배의 공격력 합을 얻는다.
			attackPower = dc.getSumShipAttack(user2db);
			System.out.println(attackPower);
			//배의 공격력 만큼 해당 은하의 체력을 깍는다.
			dc.attackGalaxy(attackPower, ramdomGalaxy);
		}
	}
}
