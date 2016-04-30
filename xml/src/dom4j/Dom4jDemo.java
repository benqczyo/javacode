package dom4j;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import junit.framework.Assert;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class Dom4jDemo {
	
	private static Document doc = null;
	private static Dom4jDemo obj = null;
	
	@BeforeClass
	public static void initClass() throws DocumentException {
		obj = new Dom4jDemo();
		SAXReader reader = new SAXReader();
		obj.doc = reader.read("books.xml");
	}
	
	@AfterClass
	public static void destoryClass() {
		obj = null;
	}
	
	@Test
	public void fun1() throws DocumentException {
		Element root = obj.doc.getRootElement();
		Assert.assertEquals("毕向东", ((Element) root.elements("书").get(1)).element("作者").getTextTrim());
	}
	
	
	@Test
	public void fun11() throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read("books.xml");
		Node authorNode = doc.selectSingleNode("//书[2]/作者");
		Assert.assertEquals("毕向东", authorNode.getText());
	}
	
	/*public void loop(Element element, String deep) {
		System.out.println(deep + element.getName());
		for(Iterator i = ((Element) element).elementIterator(); i.hasNext();) {
			loop((Element) i.next(), "\t" + deep);
		}
	}*/
	
	public void loop(Element element, String deep) {
		System.out.println(deep + element.getName());
		for (int i = 0; i < element.nodeCount(); i++) {
			Node childNode = element.node(i);
			if (childNode instanceof Element) {
				loop((Element) childNode, "\t" + deep);
			}
		}
	}
	
	@Test
	public void fun2() throws DocumentException {
		loop((Element) obj.doc.getRootElement(), "");
	}
	
	@Test
	public void fun22() {
		List<Node> nodes = obj.doc.selectNodes("//*");
		for (Node node : nodes) {
			if (node instanceof Element)
				System.out.println(node.getText());
		}
	}
	
	@Test
	public void fun3() throws IOException {
		Element root = obj.doc.getRootElement();
		((Element) root.elements("书").get(1)).element("售价").setText("99.00");
		OutputFormat of =  OutputFormat.createPrettyPrint();
		of.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream("books.xml"), of);
		writer.write(obj.doc);
		writer.close();
		Assert.assertEquals("99.00", ((Element) root.elements("书").get(1)).element("售价").getTextTrim());
	}
	
	@Test
	public void fun4() throws IOException {
		Element root = obj.doc.getRootElement();
		Element firstBook = (Element) root.elements("书").get(1);
		firstBook.addElement("批发价").setText("10.00");
		Element newElement = DocumentHelper.createElement("内部价");
		newElement.setText("1.00");
		firstBook.elements().set(2, newElement);
		OutputFormat of =  OutputFormat.createPrettyPrint();
		of.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream("books.xml"), of);
		writer.write(obj.doc);
		writer.close();
		Assert.assertEquals("10.00", ((Element) root.elements("书").get(1)).element("批发价").getTextTrim());
	}
	
	@Test
	public void fun5() throws IOException {
		Element root = obj.doc.getRootElement();
		Element firstBook = (Element) root.elements("书").get(1);
		for (Element e : (List<Element>) firstBook.elements("批发价")) {
			firstBook.remove(e);
		}
		for (Iterator i = firstBook.elementIterator("内部价"); i.hasNext();) {
			firstBook.remove((Element) i.next());
		}
		OutputFormat of =  OutputFormat.createPrettyPrint();
		of.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream("books.xml"), of);
		writer.write(obj.doc);
		writer.close();
	}
	
}
