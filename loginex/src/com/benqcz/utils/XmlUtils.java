package com.benqcz.utils;

import java.io.File;
import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlUtils {
	
	private String path;
	private Document doc;
	private String encoding;
	
	private XmlUtils() {}
	
	private XmlUtils(String path, String encoding) {
		this.path = path;
		this.encoding = encoding;
	}
	
	public static XmlUtils getUtilInstance(String path, String encoding) {
		return new XmlUtils(path, encoding);
	}
	
	public Document open() {
		try {
			doc = new SAXReader().read(path);
		} catch (DocumentException e) {
			e.printStackTrace();
			new RuntimeException(e);
		}
		return doc;
	}
	
	public void write2Xml() {
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding(encoding);
			XMLWriter writer = new XMLWriter(new FileOutputStream(path), format);
			writer.write(doc);
			writer.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
