package database;

import java.util.HashMap;

public class DatabaseMapping {
	private HashMap<String, Shard> map = new HashMap<String, Shard>();
	
	private String driver;
	private String url;
	private String id;
	private String pw;
	private String name;

	public DatabaseMapping() {
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost/popidb";
		id = "popi";
		pw = "db1004";
		name = "shard1";	
		map.put(name, new Shard(driver, url, id, pw, name));

		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost/popidb";
		id = "popi";
		pw = "db1004";
		name = "shard2";
		map.put(name, new Shard(driver, url, id, pw, name));
	}

	public Shard requestShard(String shard) {
		return map.get(shard);
	}

}
