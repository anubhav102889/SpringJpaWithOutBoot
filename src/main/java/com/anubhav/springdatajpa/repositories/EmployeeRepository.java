package com.anubhav.springdatajpa.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.anubhav.springdatajpa.entities.Employee;
import com.anubhav.springdatajpa.models.DeptEmplEmplTypeInfo;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	@Query(value = "select dept.deptId,dept.deptName,emp.empId,emp.empName,"
			+ " emp.empMail,emp.empGender,emp.empDoj,empType.empTypeId,empType.empTypeName,case empType.empTypeName"
			+ " when 'Contractor' then 'Y' else 'N' end from Employee emp,Department dept,EmployeeType empType"
			+ " where emp.department.deptId=dept.deptId and emp.employeeType.empTypeId=empType.empTypeId"
			+ " group by dept.deptName,emp.empId,emp.empName,"
			+ " emp.empMail,emp.empGender,emp.empDoj,empType.empTypeId,empType.empTypeName")
	public List<Object[]> getInfo(Pageable p);

}
