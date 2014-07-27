package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/popidb";

	// Database credentials
	static final String USER = "popi";
	static final String PASS = "db1004";

	private ConnectionPool cp;

	public DatabaseConnection() {
		try {
			Class.forName(JDBC_DRIVER).newInstance();
			cp = new ConnectionPool(DB_URL, USER, PASS);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createUser() {
		try {
			Connection conn = cp.checkout();

			String sql = "CALL sp_adduser(@uid, @gid, @dbid)";

			PreparedStatement psmt = null;
			psmt = conn.prepareStatement(sql);
			psmt.execute();

			psmt.close();
			cp.checkin(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
