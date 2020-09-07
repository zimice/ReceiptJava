import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
		System.out.println(r.getTotalCost());
		System.out.println(r.getItems());
		         
	}

}
