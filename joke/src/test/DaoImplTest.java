package test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import dao.impl.EmpDaoImpl;
import domain.Emp;

public class DaoImplTest {

	@Test
	public void testAddEmp() throws ParseException {
		Emp emp = new Emp();
		emp.setEmpno(5400);
		emp.setEname("Lord");
		emp.setJob("Mgr");
		emp.setMgr(1000);
		emp.setHiredate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("1982-08-01").getTime()));
		emp.setSal(9000);
		emp.setComm(4500);
		emp.setDeptno(20);
		System.out.println(emp);
		//assertNotNull(new EmpDaoImpl().addEmp(emp));
	}

}
