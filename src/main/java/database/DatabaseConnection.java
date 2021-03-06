package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.User;
import dto.User2DB;

public class DatabaseConnection {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/popidb";

	// Database credentials
	static final String USER = "popi";
	static final String PASS = "db1004";

	private ConnectionPool cp;
	private static DatabaseConnection dc;
	
	private DatabaseMapping map = new DatabaseMapping();
	
	private Shard s1 = map.requestShard("shard1");
	private Shard s2 = map.requestShard("shard2");

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

	public void createGlobalUser() {
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

	public User2DB selectGlobalUser(int UID) {
		System.out.println("Select User");
		User2DB user = null;
		try {
			Connection conn = cp.checkout();

			String sql = "SELECT * FROM user2db WHERE UID = ?";

			PreparedStatement psmt = null;
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, UID);
			ResultSet rs = psmt.executeQuery();
			
			if (rs.next()) {
				user = new User2DB(rs.getInt("UID"), rs.getInt("GID"), rs.getInt("DBID"));
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
	
	public void insertUser(User2DB user) {
		if(user.getDBID() == 1) {
			s1.inserUser(user);
		} else {
			s2.inserUser(user);
		}
	}
	
	public User selectUser(User2DB user2db) {
		if(user2db.getDBID() == 1) {
			return s1.selectUser(user2db);
		} else {
			return s2.selectUser(user2db);
		}
	}

	public int getLast() {
		System.out.println("Getting Last ID");
		int result = 0;
		try {
			Connection conn = cp.checkout();

			//String sql = "SELECT LAST_INSERT_ID() FROM user2db;";
			String sql = "SELECT UID FROM user2db ORDER BY UID DESC LIMIT 1";
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

	public int getSumShipAttack(User2DB user2db) {
		if(user2db.getDBID() == 1) {
			return s1.getSumShipAttack(user2db);
		} else {
			return s2.getSumShipAttack(user2db);
		}
	}

	public void attackGalaxy(int attackPower, int ramdomGalaxy) {
		if(ramdomGalaxy > 2) {
			s2.attackGalaxy(attackPower, ramdomGalaxy);
		} else {
			s1.attackGalaxy(attackPower, ramdomGalaxy);
		}
	}
}