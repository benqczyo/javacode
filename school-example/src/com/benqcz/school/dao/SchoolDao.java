package com.benqcz.school.dao;

import com.benqcz.school.domain.Student;
import com.benqcz.school.domain.Teacher;

public interface SchoolDao {
	boolean addTeacher(Teacher teacher); 
	Teacher findTeacherById(Integer id);
	boolean addStudent(Student student);
	Student findStudentById(Integer id);
}
