import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	public static Connection con = null;
	private static Database instance = new Database();

	public static Database getInstance() {
		return instance;
	}

	public void connect() {
		try {
			 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=DSLopHoc;user=sa;password=123456");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return con;
	}

}