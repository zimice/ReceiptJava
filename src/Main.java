
public class Main {

	public static void main(String[] args) {
		Item a = new Item(59.99, "Voda");
		Item b = new Item(49.99, "Kola");
		Item c = new Item(79.99, "Dzus");
		
		Receipt r = new Receipt("562", "1152");
		r.addItem(a);
		r.addItem(b);
		r.addItem(c);
		r.addItem(a);
		System.out.println(r.getItems());
		DBWorker d= new DBWorker();
		LocalSave.SaveReceipt(r);
		Receipt r1=LocalLoad.LoadReceipt("0");
		System.out.println(r1.getItems());
		System.out.println(d.getAllReceiptInfo(1));
	}
}
