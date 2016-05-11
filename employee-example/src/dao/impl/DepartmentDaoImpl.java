package dao.impl;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.benqcz.dbutils.C3P0Utils;

import dao.DepartmentDao;
import domain.Department;
import domain.Employee;

public class DepartmentDaoImpl implements DepartmentDao {
	
	private final String ADD_EMPLOYEE = "INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private final String ADD_DEPARTMENT = "INSERT INTO dept (deptno, dname, loc) VALUES (?, ?, ?)";
	
	private QueryRunner qr = new QueryRunner();

	@Override
	public boolean addDepartment(Department department) {
		boolean result = false;
		try {
			C3P0Utils.startTransaction();
			List<Object> basicDepartmentParams = new LinkedList<Object>();
			Field[] departmentFields = department.getClass().getDeclaredFields();
			for (Field departmentField: departmentFields ) {
				departmentField.setAccessible(true);
				if (!departmentField.getName().equalsIgnoreCase("employees")) {
					basicDepartmentParams.add(departmentField.get(department));
				} else {
					List<Employee> emps = (List<Employee>) departmentField.get(department);
					if (emps != null) {
						List<Object[]> basicEmployeeParams = new LinkedList<Object[]>();
						for (Employee emp : emps) {
							List<Object> innerEmployeeParams = new LinkedList<Object>();
							Field[] employeeFields = emp.getClass().getDeclaredFields();
							for (Field employeeField : employeeFields) {
								if (!employeeField.getName().equalsIgnoreCase("dept")) {
									employeeField.setAccessible(true);
									innerEmployeeParams.add(employeeField.get(emp));
								}
							}
							basicEmployeeParams.add(innerEmployeeParams.toArray());
						}
						qr.batch(C3P0Utils.open(), ADD_EMPLOYEE, basicEmployeeParams.toArray(new Object[0][0]));
					}
				}
			}
			result = qr.update(C3P0Utils.open(), ADD_DEPARTMENT, basicDepartmentParams.toArray()) == 1;
		} catch (Exception e) {
			C3P0Utils.rollback();
			throw new RuntimeException(e);
		} finally {
			C3P0Utils.commit();
			C3P0Utils.close();
		}
		return result;
	}

	@Override
	public boolean delDepartmentByNo(Integer deptno) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Department> findAllDepartments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Department findDepartmentByNo(Integer deptno) {
		// TODO Auto-generated method stub
		return null;
	}

}
