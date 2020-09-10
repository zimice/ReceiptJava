import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class LocalSave {

	public static String SaveReceipt(Receipt receipt) {
		String result = "";
		
		try  {
			FileOutputStream fos = new FileOutputStream("LocalData/" + receipt.getNumberOfReceipt() + ".dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			// write object to file
			oos.writeObject(receipt);

			// close writer
			oos.close();

		} catch (IOException e) {
			result = e.getMessage();
		}
		return result;
	}
}
