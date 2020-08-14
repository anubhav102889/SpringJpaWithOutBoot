package com.anubhav.springdatajpa.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class DeptEmplEmplTypeInfo {
	
	private int deptId;
	
	private String deptName;
	
	private int empId;
	
	private String empName;
	
	private String empMail;
	
	private String empGender;
	
	private Date empDoj;
	
	private int empTypeId;
	
	private String empTypeName;
	
	private String isContractor;

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpMail() {
		return empMail;
	}

	public void setEmpMail(String empMail) {
		this.empMail = empMail;
	}

	public String getEmpGender() {
		return empGender;
	}

	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}

	public Date getEmpDoj() {
		return empDoj;
	}

	public void setEmpDoj(Date empDoj) {
		this.empDoj = empDoj;
	}

	public int getEmpTypeId() {
		return empTypeId;
	}

	public void setEmpTypeId(int empTypeId) {
		this.empTypeId = empTypeId;
	}

	public String getEmpTypeName() {
		return empTypeName;
	}

	public void setEmpTypeName(String empTypeName) {
		this.empTypeName = empTypeName;
	}

	

	public String getIsContractor() {
		return isContractor;
	}

	public void setIsContractor(String isContractor) {
		this.isContractor = isContractor;
	}

	public DeptEmplEmplTypeInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeptEmplEmplTypeInfo(int deptId, String deptName, int empId, String empName, String empMail,
			String empGender, Date empDoj, int empTypeId, String empTypeName, String isContractor) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.empId = empId;
		this.empName = empName;
		this.empMail = empMail;
		this.empGender = empGender;
		this.empDoj = empDoj;
		this.empTypeId = empTypeId;
		this.empTypeName = empTypeName;
		this.isContractor = isContractor;
	}
	
	public DeptEmplEmplTypeInfo(int deptId, String deptName, int empId, String empName, String empMail,
			String empGender, Date empDoj, int empTypeId, String empTypeName) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.empId = empId;
		this.empName = empName;
		this.empMail = empMail;
		this.empGender = empGender;
		this.empDoj = empDoj;
		this.empTypeId = empTypeId;
		this.empTypeName = empTypeName;
	}

}
