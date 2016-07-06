package yo.benqcz.exam.test;

import yo.benqcz.exam.dao.Dao;
import yo.benqcz.exam.dao.impl.DaoImpl;
import yo.benqcz.exam.domain.Student;

public class Test {
	
	public static void main(String[] args) {
		/*Student student = new Student();
		student.setSid("9987");
		student.setName("amanda");
		student.setLocation("shanghai");
		student.setEid("1024");
		student.setGrade("88");
		new DaoImpl().addStudent(student);*/
		//new DaoImpl().deleteStudentById("9987");
		new DaoImpl().findStudentById("10001");
	}

}
