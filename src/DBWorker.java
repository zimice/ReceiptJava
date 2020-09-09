import java.sql.*;

public class DBWorker {
	private final String Default_Url = "jdbc:sqlite:C:DB/Receipt.db";
	private Connection conn;

	public DBWorker() {
		conn = connect();
	}

	public DBWorker(String url) {
		conn = connect(url);
	}

	private Connection connect() {
		return this.connect(Default_Url);
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

	public String getReceiptIdsandNumbers() {
		String result = "", sql = "SELECT id,numberOfReceipt FROM receipt";
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				result+=rs.getInt("id")+"\t"+rs.getString("numberOfReceipt");
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return result;
	}

}
