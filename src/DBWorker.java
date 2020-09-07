import java.sql.*;

public class DBWorker {
	private final String Default_Url = "jdbc:sqlite:/DB/receipt.db";
	private Connection con;
	
	public DBWorker() {
		con = connect();
	}
	public DBWorker(String url) {
		con = connect(url);
	}
	private Connection connect() {
		String url = "jdbc:sqlite:C://sqlite/db/test.db";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return conn;
	}

	private Connection connect(String url) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return conn;
	}

}
