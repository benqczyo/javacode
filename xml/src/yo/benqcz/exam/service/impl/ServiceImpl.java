package yo.benqcz.exam.service.impl;

import java.util.Map;

import yo.benqcz.exam.dao.Dao;
import yo.benqcz.exam.dao.impl.DaoImpl;
import yo.benqcz.exam.domain.Student;
import yo.benqcz.exam.service.Service;

public class ServiceImpl implements Service {
	
	private Dao dao = new DaoImpl();

	@Override
	public boolean addStudent(Student student) {
		return dao.addStudent(student);
	}

	@Override
	public boolean deleteStudentById(String sid) {
		return dao.deleteStudentById(sid);
	}

	@Override
	public Map<String, Student> findAllStudents() {
		return dao.findAllStudents();
	}

	@Override
	public Student findStudentById(String sid) {
		return dao.findStudentById(sid);
	}

}
