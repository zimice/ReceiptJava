
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.Hashtable;

public class Receipt implements Serializable {
	private String dateOfCreation;
	private String dic;
	private String address;
	private  Currency currency;
	private Hashtable<String, Item> items;
	private int numberOfReceipt;

	public int getNumberOfReceipt() {
		return numberOfReceipt;
	}

	public String getAddress() {
		return address;
	}

	public String getDateOfCreation() {
		return dateOfCreation;
	}

	public String getDic() {
		return dic;
	}

	public void setDic(String dic) {
		this.dic = dic;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private static Item convertToSameCurrency(Item itemToConvert) {
		itemToConvert.setPrice(Currency.toDoubleCrypto(itemToConvert.getCurrency(), itemToConvert.getPrice()));
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

	public int numberOfItems() {
		return items.size();
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

	public Receipt(String dateOfCreation, String dic, Hashtable<String, Item> items, Currency currency) {
		super();
		this.dateOfCreation = dateOfCreation;
		this.dic = dic;
		this.items = items;
		this.currency = currency;
		items = new Hashtable<>();
	}

	@SuppressWarnings("deprecation")
	public Receipt(String dic, String numberOfReceipt) {
		super();
		this.dic = dic;
		LocalDateTime now = LocalDateTime.now();
		this.dateOfCreation = new Date(now.getYear(), now.getMonthValue(), now.getDayOfMonth()).toString();
		this.address = "local";
		this.currency = Currency.czk;
		items = new Hashtable<>();
	}

	public Receipt(String dic, String numberOfReceipt, Currency currency) {
		super();
		this.dic = dic;
		LocalDateTime now = LocalDateTime.now();
		this.dateOfCreation = new Date(now.getYear(), now.getMonthValue(), now.getDayOfMonth()).toString();
		this.address = "local";
		this.currency = currency;
		items = new Hashtable<>();
	}

	public Receipt(int numberOfReceipt, String dateOfCreation, String dic, String address, String currency) {
		this.numberOfReceipt = numberOfReceipt;
		this.dateOfCreation = dateOfCreation;
		this.dic = dic;
		this.address = address;
		this.currency = Currency.valueOf(currency);
	}

	public Currency getCurrency() {
		return this.currency;
	}
}
