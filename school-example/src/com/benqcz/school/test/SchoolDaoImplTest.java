package com.benqcz.school.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.benqcz.school.dao.impl.SchoolDaoImpl;
import com.benqcz.school.domain.Student;
import com.benqcz.school.domain.Teacher;

public class SchoolDaoImplTest {

	@Test
	public void testAddStudent() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddTeacher() {
		Teacher teacher = new Teacher();
		teacher.setId(1);
		teacher.setName("ÕÅÈı");
		
		Student student1 = new Student();
		student1.setId(1);
		student1.setName("ÖÜ×ó×ó");
		
		Student student2 = new Student();
		student2.setId(2);
		student2.setName("³ÂÓÒÓÒ");
		
		teacher.getStudents().add(student1);
		teacher.getStudents().add(student2);
		
		new SchoolDaoImpl().addTeacher(teacher);
	}

	@Test
	public void testFindStudentById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindTeacherById() {
		Teacher teacher = new SchoolDaoImpl().findTeacherById(1);
		System.out.println(teacher);
	}

}
