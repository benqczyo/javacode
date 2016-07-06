package test;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.junit.Assert;
import org.junit.Test;

import com.benqcz.dbutils.C3P0Utils;


import dao.impl.EmpDaoImpl;
import domain.Emp;

public class AllTest {

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
		assertNotNull(new EmpDaoImpl().addEmp(emp));
	}
	
	@Test
	public void testDelEmp() {
		Assert.assertEquals(true, new EmpDaoImpl().delEmpByNo(5400));
	}

	@Test
	public void testFindAllEmp() {
		List<Emp> emps = new EmpDaoImpl().findAllEmps();
		assertNotNull(emps);
	}
	
	@Test
	public void testFindEmpByNo() {
		Emp emp = new EmpDaoImpl().findEmpByNo(5400);
		assertNotNull(emp);
	}
	
	@Test
	public void testInsertRandomDate() {
		Assert.assertEquals(true, new EmpDaoImpl().addRandomData());
	}
	
	@Test
	public void testFindEmpMap() throws SQLException {
		Map<Object, Map<String, Object>> emps = null;
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource(), true);
		emps = queryRunner.query("SELECT * FROM emp", new KeyedHandler("empno"));
		for (Entry<Object, Map<String, Object>> inner : emps.entrySet()) {
			Map<String, Object> value = inner.getValue();
			for (Object o : value.values()) {
				System.out.println(o);
			}
		}
	}
	
}
