package dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.benqcz.dbutils.C3P0Utils;


import dao.EmpDao;
import domain.Emp;

public class EmpDaoImpl implements EmpDao {
	
	private final String FIND_EMP_BY_EMPNO = "SELECT empno, ename, job, mgr, hiredate, sal, comm, deptno FROM emp WHERE empno = ?";
	private final String FIND_ALL_EMP = "SELECT empno, ename, job, mgr, hiredate, sal, comm, deptno FROM emp";
	private final String ADD_EMP = "INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private final String DEL_EMP = "DELETE FROM emp WHERE empno = ?";
	
	private QueryRunner queryRunner = new QueryRunner();

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
			queryRunner.update(C3P0Utils.open(), ADD_EMP, params.toArray());
			result = emp;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		return result;
	}

	@Override
	public boolean delEmpByNo(int empno) {
		try {
			return queryRunner.update(C3P0Utils.open(), DEL_EMP, empno) == 1;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Emp> findAllEmps() {
		try {
			return queryRunner.query(C3P0Utils.open(), FIND_ALL_EMP, new BeanListHandler<Emp>(Emp.class));
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	@Override
	public Emp findEmpByNo(int empno) {
		try {
			return queryRunner.query(C3P0Utils.open(), FIND_EMP_BY_EMPNO, new BeanHandler<Emp>(Emp.class), empno);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public boolean addRandomData() {
		boolean result = false;
		Object[][] params = new Object[][] {
				new Object[] {new Random().nextInt(3000), "random", "random", 2999, new java.sql.Date(System.currentTimeMillis()), 5000, 4000, 20},
				new Object[] {new Random().nextInt(3000), "random", "random", 2999, new java.sql.Date(System.currentTimeMillis()), 5000, 4000, 20},
				new Object[] {new Random().nextInt(3000), "random", "random", 2999, new java.sql.Date(System.currentTimeMillis()), 5000, 4000, 20},
		};
		try {
			queryRunner.batch(C3P0Utils.open(), ADD_EMP, params);
			result = true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

}
