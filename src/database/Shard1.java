package database;

import java.sql.SQLException;

public class Shard1 {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://10.73.45.75/popidb";

	// Database credentials
	static final String USER = "popi";
	static final String PASS = "db1004";

	private ConnectionPool cp;
	private static Shard1 s1;

	static public Shard1 getInstance() {
		if (s1 == null)
			s1 = new Shard1();

		return s1;
	}

	private Shard1() {
		try {
			Class.forName(JDBC_DRIVER).newInstance();
			cp = new ConnectionPool(DB_URL, USER, PASS);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
