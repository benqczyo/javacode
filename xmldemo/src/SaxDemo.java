import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class SaxDemo {
	public static void main(String[] args) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			XMLReader reader = (XMLReader) parser.getXMLReader();
			reader.setContentHandler(new ContentHandler() {
				
				boolean isAuthor = false;
				int index = 0;
				
				@Override
				public void startPrefixMapping(String prefix, String uri)
						throws SAXException {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void startElement(String uri, String localName, String qName,
						Attributes atts) throws SAXException {
					
					if ("作者".equalsIgnoreCase(qName))
						isAuthor = true;
				}
				
				@Override
				public void startDocument() throws SAXException {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void skippedEntity(String name) throws SAXException {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void setDocumentLocator(Locator locator) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void processingInstruction(String target, String data)
						throws SAXException {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void ignorableWhitespace(char[] ch, int start, int length)
						throws SAXException {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void endPrefixMapping(String prefix) throws SAXException {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void endElement(String uri, String localName, String qName)
						throws SAXException {
					if ("作者".equalsIgnoreCase(qName)) {
						index++;
					}
					isAuthor = false;
				}
				
				@Override
				public void endDocument() throws SAXException {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void characters(char[] ch, int start, int length)
						throws SAXException {
					if (isAuthor && index == 1)
						System.out.println(new String(ch, start, length));
				}
			});
			reader.parse(new InputSource(new FileInputStream(new File("book.xml"))));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
}
