import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class PlusBufferedReader extends BufferedReader {
	
	public PlusBufferedReader(BufferedReader in) {
		super(in);
	}

	@Override
	public String readLine() throws IOException {
		String result = super.readLine();
		if (result != null) result = String.format("+%s", result);
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		PlusBufferedReader reader = new PlusBufferedReader(new LineBufferedReader(new BufferedReader(
				new FileReader("src/data.txt"))));
		
		String data = null;
		while ((data = reader.readLine()) != null) {
			System.out.println(data);
		}
		reader.close();
	}
	
}
