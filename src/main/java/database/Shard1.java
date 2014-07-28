package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.User;

public class Shard1 {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/popidb";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "";

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

	public void inserUser(User user) {
		System.out.println("Insert User to shard1");
		try {
			Connection conn = cp.checkout();

			String sql = "CALL sp_addship( ? , ? )";

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
