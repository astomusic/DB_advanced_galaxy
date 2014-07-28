package database;

import dto.User;
import dto.User2DB;

public class AttackGalaxy extends Thread{
	//유저를 하나 랜덤으로 뽑고 (1~getLast())
	//해당 유저가 있는 샤드에서 해당 유저를 선택 (필요한가?)
	//은하를 랜덤으로 하나뽑는다 (1~4) 자신의 은하제외
	//해당 유저의 배의 공격력 합을 얻는다.
	//해당 은하의 hp에서 해당 유저가 가지고 있는 배들의 공격력를 뺀 나머지를 구한
	//구한값을 shard1과 shard2의 해당 은하에 hp에 업데이트한다.
	//hp를 얻고 값을 업데이트하는 동안 어떻게 2개의 샤드에 transition을 유지하고 동기화 할것인가?
	//반복
	private final int galaxy = 4;
	
	public void run() {
		System.out.println("AttackGalaxy Thread Start");
		DatabaseConnection dc = DatabaseConnection.getInstance();
		
		//유저를 하나 랜덤으로 뽑고 (1~getLast())
		int last = dc.getLast();
		int ramdomUser  = (int) Math.random() * last + 1;
		User2DB user2db = dc.selectGlobalUser(ramdomUser);
		
		//해당 유저가 있는 샤드에서 해당 유저를 선택
		User user = dc.selectUser(user2db);
		
		//은하를 랜덤으로 하나뽑는다 (1~4) 자신의 은하제외
		int ramdomGalaxy  = (int) Math.random() * galaxy + 1;
		int userGalaxy = user2db.getGID();
		while(ramdomGalaxy == userGalaxy) { 
			ramdomGalaxy  = (int) Math.random() * galaxy + 1;
		}
		
		
		
		
		
	}
}
