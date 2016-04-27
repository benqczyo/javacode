package yo.benqcz.xmldb.service;

import java.util.Map;

import yo.benqcz.xmldb.domain.Student;

public interface Service {
	public void addStudent(Student student) throws Exception;
	public void deleteStudentById(String id) throws Exception; 
	public Student findStudentById(String id) throws Exception;
	public Map<String, Student> findAllStudents() throws Exception;
	public void save() throws Exception;
}
