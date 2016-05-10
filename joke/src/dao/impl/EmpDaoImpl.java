package dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import utils.C3P0Utils;

import dao.EmpDao;
import domain.Emp;

public class EmpDaoImpl implements EmpDao {
	
	private final String ADD_EMP = "INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	
	private QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource(), true);

	@Override
	public Emp addEmp(Emp emp) {
		Emp result = null;
		try {
			List<Object> params = new LinkedList<Object>();
			Field[] fields = emp.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				params.add(field.get(emp));
			}
			queryRunner.update(ADD_EMP, params.toArray());
			result = emp;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		return result;
	}

	@Override
	public Emp delEmpByNo(int empno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Emp> findAllEmps() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Emp findEmpByNo(int empno) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) throws ParseException {
		Emp emp = new Emp();
		emp.setEmpno(5400);
		emp.setEname("Lord");
		emp.setJob("Mgr");
		emp.setMgr(1000);
		emp.setHiredate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("1982-08-01").getTime()));
		emp.setSal(9000);
		emp.setComm(4500);
		emp.setDeptno(20);
		//System.out.println(emp);
		new EmpDaoImpl().addEmp(emp);
	}

}
