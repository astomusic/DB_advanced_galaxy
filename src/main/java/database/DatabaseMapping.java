package database;

import java.util.HashMap;

public class DatabaseMapping {
	private HashMap<String, Shard> map = new HashMap<String, Shard>();
	
	private String driver;
	private String url;
	private String id;
	private String pw;

	public DatabaseMapping() {
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost/popidb";
		id = "popi";
		pw = "db1004";

		map.put("shard1", new Shard(driver, url, id, pw));

		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost/popidb";
		id = "popi";
		pw = "db1004";

		map.put("shard2", new Shard(driver, url, id, pw));
	}

	public Shard requestShard(String shard) {
		return map.get(shard);
	}

}
