package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.User;
import dto.User2DB;

public class Shard {
	private ConnectionPool cp;
	private String shardName;

	public Shard(String driver, String url, String id, String pw, String name) {
		try {
			Class.forName(driver).newInstance();
			cp = new ConnectionPool(url, id, pw);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.shardName = name;
	}

	public void inserUser(User2DB user) {
		System.out.println("Insert User to " + shardName);
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

	public User selectUser(User2DB user2db) {
		System.out.println("Select User from " + shardName);
		User user = null;
		try {
			Connection conn = cp.checkout();

			String sql = "SELECT * FROM user WHERE UID = ?";

			PreparedStatement psmt = null;
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, user2db.getUID());
			ResultSet rs = psmt.executeQuery();
			
			if (rs.next()) {
				user = new User(rs.getInt("UID"), rs.getInt("GID"));
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

	public int getSumShipAttack(User2DB user2db) {
		System.out.println("Get Sum of attckpower from" + shardName + " - " +user2db.getUID());
		int sum = 0;
		try {
			Connection conn = cp.checkout();

			String sql = "SELECT sum(ATK) FROM ship WHERE UID = ?";

			PreparedStatement psmt = null;
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, user2db.getUID());
			ResultSet rs = psmt.executeQuery();
			
			if (rs.next()) {
				sum = rs.getInt(1);
			}

			rs.close();
			psmt.close();
			cp.checkin(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sum;
	}

	public void attackGalaxy(int attackPower, int ramdomGalaxy) {
		System.out.println(ramdomGalaxy + " Galaxy attacked " + attackPower + " damaged " );
		try {
			Connection conn = cp.checkout();

			String sql = "CALL sp_attackGalaxy( ? , ? )";

			PreparedStatement psmt = null;
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, attackPower);
			psmt.setInt(2, ramdomGalaxy);
			psmt.execute();

			psmt.close();
			cp.checkin(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
