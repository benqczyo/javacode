package com.benqcz.discuss.util.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public abstract class XmlUtil {
	
	private static String dbFilePath = "";
	
	static {
		dbFilePath = XmlUtil.class.getClassLoader().getResource("users.xml").getPath();
		System.out.println(dbFilePath);
	}
	
	public static Document getDocument() {
		try {
			return new SAXReader().read(dbFilePath);
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static void write2Xml(Document document) {
		/*OutputFormat format = OutputFormat.createCompactFormat();
		format.setEncoding("utf-8");*/
		try {
			XMLWriter writer = new XMLWriter(new FileOutputStream(dbFilePath), OutputFormat.createCompactFormat());
			writer.write(document);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
