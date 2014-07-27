package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.User;

public class Shard2 {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/popidb";

	// Database credentials
	static final String USER = "popi";
	static final String PASS = "db1004"; //881214 xmfoqvj

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
	
	public void inserUser(User user) {
		System.out.println("Insert User to shard2");
		try {
			Connection conn = cp.checkout();

			String sql = "INSERT INTO user VALUES (?, ?)";

			PreparedStatement psmt = null;
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, user.getUID());
			psmt.setInt(2, user.getGID());
			psmt.execute();

			psmt.close();
			cp.checkin(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
