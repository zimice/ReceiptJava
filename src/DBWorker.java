import java.sql.*;

public class DBWorker {
	private final String Default_Url = "jdbc:sqlite:DB/Receipt.db";
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

	public String getAllReceiptInfo(int receiptNumber) {
		String sql = "SELECT r.id,r.dateOfCreation,r.dic,rc.name as ReceiptCurrency,i.name,i.price,ri.name as ItemCurrency,itemsInReceipt.times\r\n"
				+ "from receipt as r inner join currency as rc on r.currency_id = rc.id\r\n"
				+ " inner join itemsInReceipt on itemsInReceipt.receipt_id = r.id\r\n"
				+ " inner join item as i on itemsInReceipt.item_id = i.id\r\n"
				+ " inner join currency as ri on i.currency_id = ri.id  where itemsInReceipt.receipt_id = "
				+ receiptNumber;
		String result = "r.id\tr.dic\tr.currency\ti.name\ti.price\ti.currency\titemsInReceipt.times\n";
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				result += rs.getInt("id") + "\t" + rs.getString("dateOfCreation") + "\t" + rs.getString("dic") + "\t"
						+ rs.getString("ReceiptCurrency") + "\t" + rs.getString("name") + "\t" + rs.getDouble("price")
						+ "\t" + rs.getString("ItemCurrency") + "\t\t" + rs.getInt("times");
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return result;
	}

	public boolean itemExists(String itemName) {
		boolean result = false;
		String sql = "SELECT id FROM item where name = '" + itemName + "'";
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			if (rs.next())
				result = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return result;
	}

	public Receipt fillReceiptItems(Receipt r) {
		Receipt receipt = null;
		return receipt;
	}

	private Receipt getBlankReceipt(int id) {
		Receipt receipt = null;
		String result = "";
		String sql = "SELECT r.id as receipt_id,r.dateOfCreation,r.address,r.dic,rc.name as ReceiptCurrency,i.name,i.price,ri.name as ItemCurrency,itemsInReceipt.times\r\n"
				+ "from receipt as r inner join currency as rc on r.currency_id = rc.id\r\n"
				+ " inner join itemsInReceipt on itemsInReceipt.receipt_id = r.id\r\n"
				+ " inner join item as i on itemsInReceipt.item_id = i.id\r\n"
				+ " inner join currency as ri on i.currency_id = ri.id  where itemsInReceipt.receipt_id = " + id;
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				receipt = new Receipt(rs.getInt("receipt_id"), rs.getString("dateOfCreation"), rs.getString("dic"),
						rs.getString("address"), rs.getString("ReceiptCurrency"));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return receipt;
	}

	public Receipt getReceipt(int id) {

		Receipt result = null;
		result = getBlankReceipt(id);
		return result;

	}

	private int getIdCurrency(Currency currency) {
		int result = 0;
		String sql = "SELECT id from currency where name LIKE '%" + currency.toString() + "%'";
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				result += rs.getInt("id");
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return result;
	}

	public String insertReceipt(Receipt r) {
		String response = "";
		String sql = "INSERT INTO receipt(dateOfCreation,dic,address,currency_id) values('" + r.getDateOfCreation()
				+ "','" + r.getDic() + "','" + r.getAddress() + "','" + getIdCurrency(r.getCurrency()) + "')";
		
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.executeUpdate();
			insertReceiptItems(r,conn);
		} catch (SQLException e) {
			response = e.getMessage();
		}
		return response;
	}

	private String insertReceiptItems(Receipt r,Connection conn) throws SQLException {
		String response = "";
		//TODO listOfItems Receipt
		String sql = "INSERT INTO itemsInReceipt(times,item_id,receipt_id) values()";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();

		return response;
	}

}
