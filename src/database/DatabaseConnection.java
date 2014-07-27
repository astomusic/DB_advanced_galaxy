package database;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/popidb";

	// Database credentials
	static final String USER = "popi";
	static final String PASS = "db1004";

	public void connection() {
		try {
			Class.forName(JDBC_DRIVER).newInstance();
		} catch (Exception E) {
			System.err.println("Exception while loading driver");
			E.printStackTrace();
		}

		try {
			ConnectionPool cp = new ConnectionPool(DB_URL, USER, PASS);

			Connection[] connArr = new Connection[7];

			for (int i = 0; i < connArr.length; i++) {
				connArr[i] = cp.checkout();
				System.out.println("Checking out..." + connArr[i]);
				System.out.println("Available Connections ... " + cp.availableCount());
			}

			for (int i = 0; i < connArr.length; i++) {
				cp.checkin(connArr[i]);
				System.out.println("Checked in..." + connArr[i]);
				System.out.println("Available Connections ... " + cp.availableCount());
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main (String[] args)   
    {
		DatabaseConnection dc = new DatabaseConnection();
		dc.connection();
    }
}
