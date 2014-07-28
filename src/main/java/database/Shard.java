package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.User;

public class Shard {
	private ConnectionPool cp;

	public Shard(String driver, String url, String id, String pw) {
		try {
			Class.forName(driver).newInstance();
			cp = new ConnectionPool(url, id, pw);
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
