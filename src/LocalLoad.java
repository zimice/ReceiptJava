import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LocalLoad {

	public static Receipt LoadReceipt(String filename) {

		Receipt receipt = null;
		try {

			FileInputStream fi = new FileInputStream(new File("LocalData/" + filename + ".dat"));
			System.out.println("LocalData/" + filename + ".dat");
			ObjectInputStream oi = new ObjectInputStream(fi);
			// write object to file
			receipt = (Receipt) oi.readObject();

			// close writer
			oi.close();

		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Failed to load a receipt");
			System.err.println(e.getMessage());
		}
		return receipt;
	}
}
