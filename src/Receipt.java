
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.Hashtable;

public class Receipt {
	private Date dateOfCreation;
	private String dic;
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

	public Receipt(Date dateOfCreation, String dic, Hashtable<String, Item> items) {
		super();
		this.dateOfCreation = dateOfCreation;
		this.dic = dic;
		this.items = items;
		items = new Hashtable<>();
	}

	@SuppressWarnings("deprecation")
	public Receipt(String dic, String numberOfReceipt) {
		super();
		this.dic = dic;
		LocalDateTime now = LocalDateTime.now();
		this.dateOfCreation = new Date(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
		items = new Hashtable<>();
	}
}
