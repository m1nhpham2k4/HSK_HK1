package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	private Connection con = null;
	private String url = "jdbc:sqlserver://localhost:1433;databaseName=QLLop";
	private String user = "sa";
	private String password = "12345";
	
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
	public void connect() throws SQLException {
		con = DriverManager.getConnection(url, user, password);
		if(con != null) {
			System.out.println("Success");
		}
		else {
			System.out.println("Failed");
		}
	}
	public ConnectionDB() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws SQLException {
		new ConnectionDB().connect();
	}
}
