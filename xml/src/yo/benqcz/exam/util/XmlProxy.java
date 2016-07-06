package yo.benqcz.exam.util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlProxy {

	private static final String FILE_NAME = "exam.xml";

	public static Document getDocument() {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(FILE_NAME));
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static void write2Xml(Document doc) {
		try {
			TransformerFactory.newInstance().newTransformer().transform(
					new DOMSource(doc), new StreamResult(new File(FILE_NAME)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
