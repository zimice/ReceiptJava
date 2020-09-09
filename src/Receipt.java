
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.Hashtable;

public class Receipt {
	private Date dateOfCreation;
	private String dic;
	private String numberOfReceipt;
	private String address;
	private Currency currency;

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

	private Hashtable<String, Item> items;

	private static Item convertToSameCurrency(Item itemToConvert) throws Exception {
		if (itemToConvert.getCurrency() == Currency.czk) {
			itemToConvert.setPrice(currency == Currency.eur ? itemToConvert.getPrice()/26 : itemToConvert.getPrice());
			itemToConvert.setPrice(currency == Currency.usd ? itemToConvert.getPrice()/22 : itemToConvert.getPrice());
			return itemToConvert;
		}
		if (itemToConvert.getCurrency() == Currency.usd) {
			itemToConvert.setPrice(currency == Currency.eur ? itemToConvert.getPrice()*0.85 : itemToConvert.getPrice());
			itemToConvert.setPrice(currency == Currency.czk ? itemToConvert.getPrice()*22 : itemToConvert.getPrice());
			return itemToConvert;
		}
		if (itemToConvert.getCurrency() == Currency.eur) {
			itemToConvert.setPrice(currency == Currency.usd ? itemToConvert.getPrice()*1.18 : itemToConvert.getPrice());
			itemToConvert.setPrice(currency == Currency.czk ? itemToConvert.getPrice()*26: itemToConvert.getPrice());
			return itemToConvert;
		}
		throw new Exception("Money is not in list of czk,usd,eur");

	}

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
		itemToAdd= convertToSameCurrency(itemToAdd);
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
		this.dateOfCreation = new Date(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
		items = new Hashtable<>();
	}
}
