package yo.benqcz.exam.dao.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import yo.benqcz.exam.dao.Dao;
import yo.benqcz.exam.domain.Student;
import yo.benqcz.exam.util.XmlProxy;

public class DaoImpl implements Dao {

	@Override
	public boolean addStudent(Student student) {
		boolean result = false;
		try {
			Document doc = XmlProxy.getDocument();
			Element studentNode = doc.createElement("student");
			Field[] fields = Student.class.getDeclaredFields();
			for (Field f : fields) {
				String fieldName = f.getName();
				Element fieldNode = doc.createElement(fieldName);
				PropertyDescriptor dp = null;
				dp = new PropertyDescriptor(fieldName, Student.class);
				fieldNode.setTextContent((String) dp.getReadMethod().invoke(student, null));
				studentNode.appendChild(fieldNode);
			}
			Element root = (Element) doc.getElementsByTagName("exam").item(0);
			root.appendChild(studentNode);
			XmlProxy.write2Xml(doc);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

	@Override
	public boolean deleteStudentById(String sid) {
		boolean result = false;
		try {
			Document doc = XmlProxy.getDocument();
			Element root = (Element) doc.getElementsByTagName("exam").item(0);
			NodeList nodes = doc.getElementsByTagName("sid");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getTextContent().equalsIgnoreCase(sid)) {
					root.removeChild(node.getParentNode());
				}
			}
			XmlProxy.write2Xml(doc);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

	@Override
	public Map<String, Student> findAllStudents() {
		Map<String, Student> result = null;
		try {
			Document doc = XmlProxy.getDocument();
			NodeList nodes = doc.getElementsByTagName("student");
			for (int i = 0; i < nodes.getLength(); i++) {
				if (result == null) result = new HashMap<String, Student>();
				Student student = new Student();
				NodeList fieldNodes = nodes.item(i).getChildNodes();
				for (int j = 0; j < fieldNodes.getLength(); j++) {
					if (fieldNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
						Node fieldNode = fieldNodes.item(j);
						PropertyDescriptor dp = new PropertyDescriptor(fieldNode.getNodeName(), Student.class);
						dp.getWriteMethod().invoke(student, fieldNode.getTextContent());
					}
				}
				result.put(student.getSid(), student);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

	@Override
	public Student findStudentById(String sid) {
		Student result = null;
		try {
			Document doc = XmlProxy.getDocument();
			NodeList nodes = doc.getElementsByTagName("sid");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getTextContent().equalsIgnoreCase(sid)) {
					result = new Student();
					NodeList fieldNodes = node.getParentNode().getChildNodes();
					for (int j = 0; j < fieldNodes.getLength(); j++) {
						Node fieldNode = fieldNodes.item(j);
						if (fieldNode.getNodeType() == Node.ELEMENT_NODE) {
							PropertyDescriptor dp = new PropertyDescriptor(fieldNode.getNodeName(), Student.class);
							dp.getWriteMethod().invoke(result, fieldNode.getTextContent());
						}
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return result;
	}

	
	
}
