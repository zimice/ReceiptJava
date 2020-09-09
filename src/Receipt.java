
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.Hashtable;

public class Receipt {
	private Date dateOfCreation;
	private String dic;
	private String numberOfReceipt;
	private String address;
	private static Currency currency;
	private Hashtable<String, Item> items;

	public String getAddress() {
		return address;
	}

	public Date getDateOfCreation() {
		return dateOfCreation;
	}

	public String getDateOfCreationToString() {
		return dateOfCreation.toString();
	}

	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public String getDic() {
		return dic;
	}

	public void setDic(String dic) {
		this.dic = dic;
	}

	public String getNumberOfReceipt() {
		return numberOfReceipt;
	}

	public void setNumberOfReceipt(String numberOfReceipt) {
		this.numberOfReceipt = numberOfReceipt;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private static Item convertToSameCurrency(Item itemToConvert) {
		try {
			itemToConvert.setPrice(Currency.toDoubleCrypto(itemToConvert.getCurrency(), itemToConvert.getPrice()));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return itemToConvert;
	}

	public double getTotalCost() {
		int sum = 0;
		Enumeration<String> keys = items.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			sum += Currency.fromDoubleCrypto(currency, items.get(key).getPrice());
		}
		return sum;
	}

	public void addItem(Item itemToAdd) {
		itemToAdd = convertToSameCurrency(itemToAdd);
		if (items.get(itemToAdd.getName()) != null) {
			Item savedItem = items.get(itemToAdd.getName());
			items.get(savedItem.getName()).addTimes(savedItem.getTimes());
		} else {
			items.put(itemToAdd.getName(), itemToAdd);
		}
	}

	public String getStringOfItems() {
		String result = "";
		Enumeration<String> keys = items.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			result += items.get(key).toString() + "\n";
		}
		return result;
	}
	public Hashtable<String,Item> getItems() {
		return items;
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
		this.dateOfCreation = new Date(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
		items = new Hashtable<>();
	}
}
