package Bai2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	public static Connection con = null;
	private static Database instance = new Database();
	
	public static Database getInstance() {
		return instance;
	}
	
	public static void connect() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=QLLopHoc_Java;user=sa;password=6f6562;encrypt=true;trustServerCertificate=true";

		
		try {
			con = DriverManager.getConnection(url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		connect();
		return con;
	}
}
