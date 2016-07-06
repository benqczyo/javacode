package test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import dao.impl.DepartmentDaoImpl;
import domain.Department;
import domain.Employee;

public class DepartmentDaoImplTest {

	@Test
	public void testAddDepartment() {
		Department dept = new Department();
		dept.setDeptno(60);
		dept.setDname("技术测试部");
		dept.setLoc("中国福州");
		
		Employee e1 = new Employee();
		e1.setEmpno(6001);
		e1.setEname("张三");
		e1.setJob("测试员");
		e1.setMgr(6000);
		e1.setHiredate(new Date(System.currentTimeMillis()));
		e1.setSal(10000);
		e1.setComm(3000);
		e1.setDeptno(60);
		
		Employee e2 = new Employee();
		e2.setEmpno(6002);
		e2.setEname("李四");
		e2.setJob("架构师");
		e2.setMgr(6000);
		e2.setHiredate(new Date(System.currentTimeMillis()));
		e2.setSal(13000);
		e2.setComm(5000);
		e2.setDeptno(60);
	
		List<Employee> emps = new LinkedList<Employee>();
		emps.add(e1);
		emps.add(e2);
		
		dept.setEmployees(emps);
		
		new DepartmentDaoImpl().addDepartment(dept);
	}

	@Test
	public void testDelDepartmentByNo() {
		new DepartmentDaoImpl().delDepartmentByNo(60);
	}

	@Test
	public void testFindAllDepartments() {
		List<Department> depts = null;
		assertNotNull(depts = new DepartmentDaoImpl().findAllDepartments());
	}

	@Test
	public void testFindDepartmentByNo() {
		Department dept = null;
		assertNotNull(dept = new DepartmentDaoImpl().findDepartmentByNo(60));
		List<Employee> emps = dept.getEmployees();
		for (Employee emp : emps) {
			System.out.println(emp);
		}
	}

}
