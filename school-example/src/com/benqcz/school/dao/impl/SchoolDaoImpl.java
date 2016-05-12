package com.benqcz.school.dao.impl;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.benqcz.dbutils.C3P0Utils;
import com.benqcz.school.dao.SchoolDao;
import com.benqcz.school.domain.Student;
import com.benqcz.school.domain.Teacher;
import com.mchange.v2.c3p0.cfg.C3P0Config;

public class SchoolDaoImpl implements SchoolDao {

	private final String FIND_TEACHER_BY_ID = "SELECT id, name FROM teacher WHERE id = ?";
	private final String ADD_TEACHER = "INSERT INTO teacher (id, name) VALUES (?, ?)";
	private final String ADD_STUDENT = "INSERT INTO student (id, name) VALUES (?, ?)";
	private final String ADD_RELATIONSHIP = "INSERT INTO teacher_student (t_id, s_id) VALUES (?, ?)";
	private final String FIND_STUDENT_BY_ID = "SELECT id, name FROM student WHERE id = ?";
	private final String FIND_STUDENTS_BY_TEACHER_ID = "SELECT id, name FROM student WHERE id IN (SELECT s_id FROM teacher_student WHERE t_id = ?)";
	private final String FIND_STUDENT = "SELECT count(*) FROM student WHERE id = ?";

	private QueryRunner qr = new QueryRunner();

	@Override
	public boolean addStudent(Student student) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addTeacher(Teacher teacher) {
		boolean result = false;
		try {
			C3P0Utils.startTransaction();
			List<Object> basicTeaParams = new LinkedList<Object>();
			Field[] teaFields = teacher.getClass().getDeclaredFields();
			Field stuInfo = null;
			for (Field teaField : teaFields) {
				teaField.setAccessible(true);
				if (teaField.getName().equalsIgnoreCase("students")) {
					stuInfo = teaField;
					continue;
				}
				basicTeaParams.add(teaField.get(teacher));
			}
			qr.update(C3P0Utils.open(), ADD_TEACHER, basicTeaParams.toArray());
			if (stuInfo != null) {
				List<Student> stus = (List<Student>) stuInfo.get(teacher);
				if (stus != null && stus.size() > 0) {
					List<Object[]> stuBatchParams = new LinkedList<Object[]>();
					List<Object[]> relationBatchParams = new LinkedList<Object[]>();
					for (Student stu : stus) {
						Object searchResult = qr
								.query(C3P0Utils.open(), FIND_STUDENT,
										new ScalarHandler(1), stu.getId());
						if (searchResult != null) {
							List<Object> basicStuParams = new LinkedList<Object>();
							Field[] stuFields = stu.getClass()
									.getDeclaredFields();
							for (Field stuField : stuFields) {
								stuField.setAccessible(true);
								if (!stuField.getName().equalsIgnoreCase(
										"teachers")) {
									basicStuParams.add(stuField.get(stu));
								}
							}

							stuBatchParams.add(basicStuParams.toArray());
						}
						List<Object> basicRelParams = new LinkedList<Object>();
						basicRelParams.add(teacher.getId());
						basicRelParams.add(stu.getId());
						relationBatchParams.add(basicRelParams.toArray());
					}
					qr.batch(C3P0Utils.open(), ADD_STUDENT, stuBatchParams
							.toArray(new Object[0][0]));
					qr.batch(C3P0Utils.open(), ADD_RELATIONSHIP,
							relationBatchParams.toArray(new Object[0][0]));
				}

			}
			result = true;
			return result;
		} catch (Exception e) {
			C3P0Utils.rollback();
			throw new RuntimeException(e);
		} finally {
			C3P0Utils.commit();
			C3P0Utils.close();
		}
	}

	@Override
	public Student findStudentById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Teacher findTeacherById(Integer id) {
		Teacher result = null;
		try {
			C3P0Utils.startTransaction();
			result = qr.query(C3P0Utils.open(), FIND_TEACHER_BY_ID,
					new BeanHandler<Teacher>(Teacher.class), id);
			if (result != null) {
				List<Student> studs = qr.query(C3P0Utils.open(),
						FIND_STUDENTS_BY_TEACHER_ID,
						new BeanListHandler<Student>(Student.class), id);
				result.setStudents(studs);
			}
		} catch (SQLException e) {
			C3P0Utils.rollback();
			throw new RuntimeException(e);
		} finally {
			C3P0Utils.commit();
			C3P0Utils.close();
		}
		return result;
	}

}
