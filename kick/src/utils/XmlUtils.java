package utils;

import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlUtils {

	private static String dbFilePath;

	static {
		dbFilePath = XmlUtils.class.getClassLoader().getResource("users.xml").getPath();
	}

	public static Document open() {
		try {
			Document document = new SAXReader().read(dbFilePath);
			return document;
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static void save(Document doc) {
		try {
			XMLWriter writer = new XMLWriter(new FileOutputStream(dbFilePath), 
					OutputFormat.createPrettyPrint());
			writer.write(doc);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
