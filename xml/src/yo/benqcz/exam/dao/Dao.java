package yo.benqcz.exam.dao;

import java.util.Map;

import yo.benqcz.exam.domain.Student;

public interface Dao {
	public abstract boolean addStudent(Student student);
	public abstract boolean deleteStudentById(String sid);
	public abstract Student findStudentById(String sid);
	public abstract Map<String, Student> findAllStudents();
}
