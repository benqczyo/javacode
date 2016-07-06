package domain;

import java.io.Serializable;
import java.sql.Date;

/*
	EMPNO                                     NOT NULL NUMBER(4)
	ENAME                                              VARCHAR2(10)
	JOB                                                VARCHAR2(9)
	MGR                                                NUMBER(4)
	HIREDATE                                           DATE
	SAL                                                NUMBER(7,2)
	COMM                                               NUMBER(7,2)
	DEPTNO                                             NUMBER(2)
*/

public class Employee implements Serializable {
	
	private Integer empno;
	private String ename;
	private String job;
	private int mgr;
	private Date hiredate;
	private float sal;
	private float comm;
	private int deptno;
	private Department dept;

	public Employee() {}

	public Integer getEmpno() {
		return empno;
	}

	public void setEmpno(Integer empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getMgr() {
		return mgr;
	}

	public void setMgr(int mgr) {
		this.mgr = mgr;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public float getSal() {
		return sal;
	}

	public void setSal(float sal) {
		this.sal = sal;
	}

	public float getComm() {
		return comm;
	}

	public void setComm(float comm) {
		this.comm = comm;
	}
	
	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return String
				.format(
						"Employee [comm=%s, deptno=%s, empno=%s, ename=%s, hiredate=%s, job=%s, mgr=%s, sal=%s]",
						comm, deptno, empno, ename, hiredate, job, mgr,
						sal);
	}
	
}
