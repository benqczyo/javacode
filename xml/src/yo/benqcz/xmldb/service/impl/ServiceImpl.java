package yo.benqcz.xmldb.service.impl;

import java.util.Map;

import yo.benqcz.xmldb.dao.XmlDao;
import yo.benqcz.xmldb.dao.impl.XmlDaoImpl;
import yo.benqcz.xmldb.domain.Student;
import yo.benqcz.xmldb.service.Service;

public class ServiceImpl implements Service {
	
	private XmlDao dao = new XmlDaoImpl();

	@Override
	public void addStudent(Student student) throws Exception {
		dao.addStudent(student);
	}

	@Override
	public void deleteStudentById(String id) throws Exception {
		dao.deleteStudentById(id);
	}

	@Override
	public Map<String, Student> findAllStudents() throws Exception {
		return dao.findAllStudents();
	}

	@Override
	public Student findStudentById(String id) throws Exception {
		return dao.findStudentById(id);
	}

	@Override
	public void save() throws Exception {
		dao.write();
	}

	
}
