package com.tjoeun.jdbc;

public class EmpVo {
	private int empno;
	private String ename;
	private int deptno;
	private java.sql.Date hiredate;
	private int sal;
	
	
	@Override
	public String toString() {
		return String.format("%d\t%s\t%d\t%s\t%d",
				+ empno, ename, deptno, hiredate, sal);
	}
	public int getEmpno() {
		return empno;
	}
	@Override
	public boolean equals(Object obj) {
		EmpVo v = (EmpVo)obj;
		if(this.empno == v.getEmpno()) {
			return true;
		}else {
			return false;
		}
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public java.sql.Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(java.sql.Date hiredate) {
		this.hiredate = hiredate;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	
	
}
