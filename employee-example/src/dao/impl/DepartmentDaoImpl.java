package dao.impl;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.benqcz.dbutils.C3P0Utils;

import dao.DepartmentDao;
import domain.Department;
import domain.Employee;

public class DepartmentDaoImpl implements DepartmentDao {

	private final String ADD_EMPLOYEE = "INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private final String ADD_DEPARTMENT = "INSERT INTO dept (deptno, dname, loc) VALUES (?, ?, ?)";
	private final String FIND_ALL_DEPARTMENTS = "SELECT deptno, dname, loc FROM dept";
	private final String FIND_ALL_EMPLOYEES_BY_DEPARTMENT_ID = "SELECT empno, ename, job, mgr, hiredate, sal, comm, deptno FROM emp WHERE deptno = ?";
	private final String FIND_DEPARTMENT_BY_ID = "SELECT deptno, dname, loc FROM dept WHERE deptno = ?";
	private final String DEL_DEPARTMENT_BY_ID = "DELETE FROM dept WHERE deptno = ?";
	private final String DEL_ALL_EMPLOYEES_BY_DEPARTMENT_ID = "DELETE FROM emp WHERE deptno = ?";
	
	private QueryRunner qr = new QueryRunner();

	@Override
	public boolean addDepartment(Department department) {
		boolean result = false;
		try {
			// 开启事务
			C3P0Utils.startTransaction();
			// 插入部门数据
			List<Object> basicDeptParams = new LinkedList<Object>();
			Field[] deptFields = department.getClass().getDeclaredFields();
			Field empField = null;
			for (Field deptField : deptFields) {
				deptField.setAccessible(true);
				if (deptField.getName().equalsIgnoreCase("employees")) {
					empField = deptField;
					continue;
				}
				basicDeptParams.add(deptField.get(department));
			}
			qr.update(C3P0Utils.open(), ADD_DEPARTMENT, basicDeptParams
					.toArray(new Object[basicDeptParams.size()]));
			// 插入员工数据
			if (empField != null) {
				List<Employee> emps = (List<Employee>) empField.get(department);
				if (emps != null) {
					List<Object[]> basicEmpParams = new LinkedList<Object[]>();
					for (Employee emp : emps) {
						List<Object> innerEmpParams = new LinkedList<Object>();
						Field[] empFields = emp.getClass().getDeclaredFields();
						for (Field innerEmpField : empFields) {
							if (!innerEmpField.getName().equalsIgnoreCase(
									"dept")) {
								innerEmpField.setAccessible(true);
								innerEmpParams.add(innerEmpField.get(emp));
							}
						}
						basicEmpParams.add(innerEmpParams.toArray());
					}
					qr.batch(C3P0Utils.open(), ADD_EMPLOYEE, basicEmpParams
							.toArray(new Object[0][0]));
				}
			}
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
		boolean result = false;
		try {
			C3P0Utils.startTransaction();
			qr.update(C3P0Utils.open(), DEL_ALL_EMPLOYEES_BY_DEPARTMENT_ID, new Object[] { deptno });
			qr.update(C3P0Utils.open(), DEL_DEPARTMENT_BY_ID, new Object[] { deptno });
		} catch (SQLException e) {
			C3P0Utils.rollback();
		} finally {
			C3P0Utils.commit();
			C3P0Utils.close();
		}
		return result;
	}

	@Override
	public List<Department> findAllDepartments() {
		List<Department> result = null;
		try {
			result = qr.query(C3P0Utils.open(), FIND_ALL_DEPARTMENTS,
					new BeanListHandler<Department>(Department.class));
			if (result != null) {
				for (Department dept : result) {
					List<Employee> employees = qr.query(C3P0Utils.open(),
							FIND_ALL_EMPLOYEES_BY_DEPARTMENT_ID,
							new BeanListHandler<Employee>(Employee.class),
							new Object[] { dept.getDeptno() });
					for (Employee emp : employees) {
						emp.setDept(dept);
					}
					dept.setEmployees(employees);
				}
			}
			return result;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			C3P0Utils.close();
		}
	}

	@Override
	public Department findDepartmentByNo(Integer deptno) {
		Department result = null;
		try {
			result = qr.query(C3P0Utils.open(), FIND_DEPARTMENT_BY_ID,
					new BeanHandler<Department>(Department.class),
					new Object[] { deptno });
			if (result != null) {
				List<Employee> employees = qr.query(C3P0Utils.open(),
						FIND_ALL_EMPLOYEES_BY_DEPARTMENT_ID,
						new BeanListHandler<Employee>(Employee.class),
						new Object[] { result.getDeptno() });
				for (Employee emp : employees) {
					emp.setDept(result);
				}
				result.setEmployees(employees);
			}
			return result;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			C3P0Utils.close();
		}
	}

}
