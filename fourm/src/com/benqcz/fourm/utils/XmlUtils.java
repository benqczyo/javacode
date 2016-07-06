package com.benqcz.fourm.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public abstract class XmlUtils {

	private static String dbFilePath;

	static {
		dbFilePath = XmlUtils.class.getClassLoader().getResource("users.xml")
				.getPath();
	}

	public static Document getDocument() {
		try {
			Document document = new SAXReader().read(dbFilePath);
			return document;
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static void write2Xml(Document document) {
		try {
			XMLWriter writer = new XMLWriter(new FileOutputStream(dbFilePath),
					OutputFormat.createPrettyPrint());
			writer.write(document);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
