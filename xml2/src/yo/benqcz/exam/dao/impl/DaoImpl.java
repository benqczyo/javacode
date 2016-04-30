package yo.benqcz.exam.dao.impl;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import yo.benqcz.exam.dao.Dao;
import yo.benqcz.exam.domain.Student;
import yo.benqcz.exam.util.XmlProxy;

public class DaoImpl implements Dao {

	@Override
	public boolean addStudent(Student student) {
		boolean result = false;
		try {
			Document doc = XmlProxy.getDocument();
			Element newStudent = doc.getRootElement().addElement("student");
			Field[] fields = Student.class.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				newStudent.addElement(field.getName()).setText((String) field.get(student));
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
	public boolean deleteStudentById(String sid) {
		boolean result = false;
		try {
			Document doc = XmlProxy.getDocument();
			for (Node node : (List<Node>) doc.selectNodes("//sid")) {
				if (node.getText().equalsIgnoreCase(sid)) {
					doc.getRootElement().remove(node.getParent());
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student findStudentById(String sid) {
		Student result = null;
		
		return result;
	}
	
}
