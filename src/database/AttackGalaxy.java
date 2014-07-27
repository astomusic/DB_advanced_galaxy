package database;

import dto.User;

public class AttackGalaxy extends Thread{
	//유저를 하나 랜덤으로 뽑고 (1~getLast())
	//해당 유저가 있는 샤드에서 해당 유저를 선택
	//은하를 랜덤으로 하나뽑는다 (1~4) 자신의 은하제외
	//해당 은하의 hp에서 해당 유저가 가지고 있는 배들의 공격력를 뺀 나머지를 구한
	//구한값을 shard1과 shard2의 해당 은하에 hp에 업데이트한다.
	//hp를 얻고 값을 업데이트하는 동안 어떻게 2개의 샤드에 transition을 유지하고 동기화 할것인가?
	//반복
	public void run() {
		System.out.println("AttackGalaxy Thread Start");
		DatabaseConnection dc = DatabaseConnection.getInstance();
		
		int last = dc.getLast();
		int ramdomUser  = (int) Math.random() * last + 1;
		
		User user = dc.selectUser(ramdomUser);
		
		
	}
}
