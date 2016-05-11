package domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/*
DEPTNO                                    NOT NULL NUMBER(2)
DNAME                                              VARCHAR2(14)
LOC                                                VARCHAR2(13)
*/

public class Department implements Serializable {
	
	private Integer deptno;
	private String dname;
	private String loc;
	private List<Employee> employees = new LinkedList<Employee>();
	
	public Department() {}

	public Integer getDeptno() {
		return deptno;
	}

	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return String.format(
				"Department [deptno=%s, dname=%s, employees=%s, loc=%s]",
				deptno, dname, employees, loc);
	}
	
}
