package database;

import java.sql.SQLException;

public class Shard2 {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://10.73.45.53/popidb";

	// Database credentials
	static final String USER = "popi";
	static final String PASS = "xmfoqvj"; //881214

	private ConnectionPool cp;
	private static Shard2 s2;

	static public Shard2 getInstance() {
		if (s2 == null)
			s2 = new Shard2();

		return s2;
	}

	private Shard2() {
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
