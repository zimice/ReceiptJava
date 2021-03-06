import java.io.Serializable;

public class Item implements Serializable {
	private double price;
	private String name;
	private int times;
	private Currency currency;

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) { //TODO convert price
		this.currency = currency;
	}

	public int getTimes() {
		return times;
	}

	public void addTimes(int times) {
		this.times += times;
	}
	
	private static Item convertToSameCurrency(Item itemToConvert) {
		itemToConvert.setPrice(Currency.toDoubleCrypto(itemToConvert.getCurrency(), itemToConvert.getPrice()));
		return itemToConvert;
	}
	
	@Override
	public String toString() {
		return "Item [price=" + price + ", name=" + name + ", times=" + times + ", currency=" + currency + "]";
	}

	public Item(double price, String name) {
		this.price = price;
		this.name = name;
		this.times = 1;
		this.currency = Currency.czk;
	}

	public Item(double price, String name, int times) {
		this.price = price;
		this.name = name;
		this.times = times;
	}

	public double getPrice() {
		return price * times;
	}

	public void setPrice(double price) throws RuntimeException {
		if (price < 0) {
			throw new RuntimeException("Price must be higher than zero");
		} else {
			this.price = price;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
