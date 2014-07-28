package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.User;

public class DatabaseConnection {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/popidb";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "";

	private ConnectionPool cp;
	private static DatabaseConnection dc;
	
	private Shard1 s1 = Shard1.getInstance();
	private Shard2 s2 = Shard2.getInstance();

	static public DatabaseConnection getInstance() {
		if (dc == null)
			dc = new DatabaseConnection();

		return dc;
	}

	private DatabaseConnection() {
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
		System.out.println("Create New User");
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

	public User selectUser(int UID) {
		System.out.println("Select User");
		User user = null;
		try {
			Connection conn = cp.checkout();

			String sql = "SELECT * FROM user2db WHERE UID = ?";

			PreparedStatement psmt = null;
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, UID);
			ResultSet rs = psmt.executeQuery();
			
			if (rs.next()) {
				user = new User(rs.getInt("UID"), rs.getInt("GID"), rs.getInt("DBID"));
				System.out.println(user.getUID());
			}

			rs.close();
			psmt.close();
			cp.checkin(conn);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public void insertUser(User user) {
		if(user.getDBID() == 1) {
			s1.inserUser(user);
		} else {
			s2.inserUser(user);
		}
	}

	public int getLast() {
		System.out.println("Getting Last ID");
		int result = 0;
		try {
			Connection conn = cp.checkout();

			String sql = "SELECT LAST_INSERT_ID() FROM user2db;";

			PreparedStatement psmt = null;
			psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

			rs.close();
			psmt.close();
			cp.checkin(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
}