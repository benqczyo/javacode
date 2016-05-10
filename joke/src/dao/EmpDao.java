package dao;

import java.util.List;

import domain.Emp;

public interface EmpDao {
	Emp addEmp(Emp emp);
	Emp delEmpByNo(int empno);
	Emp findEmpByNo(int empno);
	List<Emp> findAllEmps();
}
