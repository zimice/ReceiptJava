
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class Print {
	private static Receipt receipt;
	private final String TEMPLATE_URL = "rsc/template.txt";
	private final String EXPORT_URL = "exported/";
	private String filename;
	private static final String[] keywords= {"$ADRESS$","$numberOfReceipt$","$dateOfCreation$","$dic$","$Currency$","$TotalPrice$"};// ,"$ITEM.NAME$","$ITEM.TIMES$","$ITEM.PRICE$","$ITEM.CURRENCY$",
	private static final String[] replacingKeywords= {receipt.getAddress(),receipt.getNumberOfReceipt(),receipt.getDateOfCreationToString(),receipt.getDic()};
	public Print(Receipt receipt,String filename) {
		this.receipt = receipt;
		this.filename = filename;
	}
	public String makeTextFile() {
		String status;
		try {
			Files.copy(Paths.get(TEMPLATE_URL),Paths.get(EXPORT_URL+filename+".txt"), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 try {
			 	
	            File f1 = new File(EXPORT_URL+"test.txt");
	            FileReader fr = new FileReader(f1);
	            BufferedReader br = new BufferedReader(fr);
	            String line = null;
	            List<String> lines = new ArrayList<String>();
	            while ((line = br.readLine()) != null) {
	                lines.add(line);
	            }
	            fr.close();
	            br.close();

	            FileWriter fw = new FileWriter(f1);
	            BufferedWriter out = new BufferedWriter(fw);
	            for(String s : lines) {
	            	s.replace("	",""); //TODO 
	                 out.write(s);
	            }
	            out.flush();
	            out.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
		status = "File created";
		return status;
	}
}
