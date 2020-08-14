package com.anubhav.springdatajpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anubhav.springdatajpa.entities.Department;
import com.anubhav.springdatajpa.models.DeptEmplEmplTypeInfo;

public interface DepartmentRepo extends JpaRepository<Department,Integer>{
	
	@Query(nativeQuery = true)
	public List<DeptEmplEmplTypeInfo> getInfo(@Param(value = "deptId")int id);

}
