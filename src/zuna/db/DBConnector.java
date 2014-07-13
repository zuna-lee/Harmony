package zuna.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnector {
	private static Connection conn;
//	public static ConnectionPool pool;

	public static Connection getConn() {
		return conn;
	}

	public static void getConnection(String db){
		
			try {
				Class.forName("org.sqlite.JDBC");
//				conn= DriverManager.getConnection("jdbc:sqlite:test.db");
				conn= DriverManager.getConnection(db);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void closeConn(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}