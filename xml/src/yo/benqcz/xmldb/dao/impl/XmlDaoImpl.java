package yo.benqcz.xmldb.dao.impl;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import yo.benqcz.xmldb.dao.XmlDao;
import yo.benqcz.xmldb.domain.Student;

public class XmlDaoImpl extends AbstractXmlDao {
	
	private static final String DB_FILE_NAME = "exam.xml";

	public XmlDaoImpl() {
		try {
			this.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Map<String, Student> union() {
		Map<String, Student> result = new HashMap<String, Student>();
		result.putAll(oldStudents);
		result.putAll(newStudents);
		return result;
	}
	
	private void read() throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new File(DB_FILE_NAME));
		NodeList list = doc.getElementsByTagName("student");
		for (int i = 0; i < list.getLength(); i++) {
			Student student = new Student();
			NodeList childs = list.item(i).getChildNodes();
			for (int j = 0; j < childs.getLength(); j++) {
				if (childs.item(j).getNodeType() == Node.ELEMENT_NODE) {
					PropertyDescriptor dp = new PropertyDescriptor(childs.item(j).getNodeName(), student.getClass());
					dp.getWriteMethod().invoke(student, childs.item(j).getTextContent());
					oldStudents.put(student.getSid(), student);
				}
			}
		}
	}
	
	public void write() throws Exception {
		System.out.println(union());
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		Element root = doc.createElement("exam");
		Field[] fields = Student.class.getDeclaredFields();
		for (Entry<String, Student> item : union().entrySet()) {
			Element studentNode = doc.createElement("student");
			for (Field f : fields) {
				String fieldName = f.getName();
				Element fieldNode = doc.createElement(fieldName);
				Student student = item.getValue();
				PropertyDescriptor dp = new PropertyDescriptor(fieldName, student.getClass());
				fieldNode.setTextContent((String) dp.getReadMethod().invoke(student, null));
				studentNode.appendChild(fieldNode);
				
			}
			root.appendChild(studentNode);
		}
		doc.appendChild(root);
		TransformerFactory tff = TransformerFactory.newInstance();
		Transformer tf = tff.newTransformer();
		tf.transform(new DOMSource(doc), new StreamResult(new File(DB_FILE_NAME)));
	}

	@Override
	public Map<String, Student> findAllStudents() throws Exception {
		return union();
	}

	@Override
	public Student findStudentById(String id) throws Exception {
		return union().get(id);
	}


	@Override
	public void addStudent(Student student) throws Exception {
		String sid = student.getSid();
		if (union().containsKey(sid) == false) {
			newStudents.put(sid, student);
		}
	}

	@Override
	public void deleteStudentById(String id) throws Exception {
		if (oldStudents.containsKey(id)) oldStudents.remove(id);
		if (newStudents.containsKey(id)) newStudents.remove(id);
	}
	
}
