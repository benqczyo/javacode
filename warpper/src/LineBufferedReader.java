import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Proxy;

public class LineBufferedReader extends BufferedReader {

	private int lineNum;

	public LineBufferedReader(BufferedReader in) {
		super(in);
	}

	@Override
	public String readLine() throws IOException {
		String data = super.readLine();
		if (data != null) data = String.format("%d: %s", lineNum++, data);
		return data;
	}

	public static void main(String[] args) throws IOException {
		LineBufferedReader reader = new LineBufferedReader(new BufferedReader(
				new FileReader("src/data.txt")));
		String data = null;
		while ((data = reader.readLine()) != null) {
			System.out.println(data);
		}
	}

}
