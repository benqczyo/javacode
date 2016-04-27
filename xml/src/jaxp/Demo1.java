package jaxp;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Demo1 {
	
	public static void getAllNodeNames(Node node, String deep) {
		short type = node.getNodeType();
		if (type == Node.DOCUMENT_NODE || type == Node.ELEMENT_NODE) {
			System.out.println(deep + node.getNodeName());
			NodeList list = node.getChildNodes();
			int length = list.getLength();
			if (list.getLength() > 0) {
				for (int i = 0; i < length; i++) {
					getAllNodeNames(list.item(i), deep + "\t");
				}
			}
		}
	}
	
	public static void setNewValue(Node node, String newValue) throws TransformerException {
		node.setTextContent(newValue);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = tf.newTransformer();
		t.transform(new DOMSource(node.getOwnerDocument()), new StreamResult(new File("user.xml")));
	}
	
	public static void addChild(Node parent, Node child) throws Exception {
		parent.appendChild(child);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = tf.newTransformer();
		t.transform(new DOMSource(parent.getOwnerDocument()), new StreamResult(new File("user.xml")));
	}
	
	public static void deleteNode(Node node) throws Exception {
		node.getParentNode().removeChild(node);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = tf.newTransformer();
		t.transform(new DOMSource(node.getOwnerDocument()), new StreamResult(new File("user.xml")));
	}
	
	public static void main(String[] args) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File("user.xml"));
		/*getAllNodeNames(document, "");
		setNewValue(document.getElementsByTagName("name").item(1), "jack");
		Element e = document.createElement("nickname");
		e.setTextContent("ben");
		addChild(document.getElementsByTagName("user").item(0), e);
		deleteNode(document.getElementsByTagName("nickname").item(0));*/
		Element e = (Element) document.getElementsByTagName("user").item(0);
	
	}
	
}
