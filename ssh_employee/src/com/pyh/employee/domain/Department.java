package com.pyh.employee.domain;

import java.util.HashSet;
import java.util.Set;

/*
 * 部门的实体
 */
public class Department {
	private Integer did;
	private String dname;
	private String ddesc;//不能叫desc，因为desc在数据库中是关键字，不能成功创建表
	//员工的集合
	private Set<Employee> employees = new HashSet<Employee>();
	public Set<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getDdesc() {
		return ddesc;
	}
	public void setDdesc(String ddesc) {
		this.ddesc = ddesc;
	}
	
}
