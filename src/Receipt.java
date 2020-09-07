
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.Hashtable;

public class Receipt {
	private Date dateOfCreation;
	private String dic;
	private String numberOfReceipt;
	private String address;
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private Hashtable<String, Item> items;

	public double getTotalCost() {
		int sum = 0;
		Enumeration<String> keys = items.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			sum += items.get(key).getPrice();
		}
		return sum;
	}

	public void addItem(Item itemToAdd) {
		if (items.get(itemToAdd.getName()) != null) {
			Item savedItem = items.get(itemToAdd.getName());
			items.get(savedItem.getName()).addTimes(savedItem.getTimes());
		} else {
			items.put(itemToAdd.getName(), itemToAdd);
		}
	}

	public String getItems() {
		String result = "";
		Enumeration<String> keys = items.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			result += items.get(key).toString() + "\n";
		}
		return result;
	}

	public Receipt(Date dateOfCreation, String dic, String numberOfReceipt, Hashtable<String, Item> items) {
		super();
		this.dateOfCreation = dateOfCreation;
		this.dic = dic;
		this.numberOfReceipt = numberOfReceipt;
		this.items = items;
		items = new Hashtable<>();
	}

	@SuppressWarnings("deprecation")
	public Receipt(String dic, String numberOfReceipt) {
		super();
		this.dic = dic;
		this.numberOfReceipt = numberOfReceipt;
		LocalDateTime now = LocalDateTime.now();
		this.dateOfCreation = new Date(now.getYear(),now.getMonthValue(),now.getDayOfMonth());
		items = new Hashtable<>();
	}
}
