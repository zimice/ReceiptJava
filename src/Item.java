
public class Item {
	private double price;
	private String name;
	private int times;
	private Currency currency;
	public int getTimes() {
		return times;
	}
	public void addTimes(int times) {
		this.times+=times;
	}
	@Override
	public String toString() {
		return "Item [price=" + price + ", name=" + name + ", times=" + times + "]";
	}
	public Item(double price,String name) {
		this.price = price;
		this.name = name;
		this.times = 1;
	}
	public Item(double price,String name,int times) {
		this.price = price;
		this.name = name;
		this.times = times;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) throws Exception {
		if(times < 1 ) {
			throw new Exception("price must be a natural number");
		}else {
			this.times = times;
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
	
	enum Currency{
		czk,
		eur,
		usd
	}
}
