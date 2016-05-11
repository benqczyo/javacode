package dao;

import java.util.List;

import domain.Department;

public interface DepartmentDao {
	boolean addDepartment(Department department);
	boolean delDepartmentByNo(Integer deptno);
	List<Department> findAllDepartments();
	Department findDepartmentByNo(Integer deptno);
}
