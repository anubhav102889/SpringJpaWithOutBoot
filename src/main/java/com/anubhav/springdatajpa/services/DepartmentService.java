package com.anubhav.springdatajpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.anubhav.springdatajpa.models.DeptEmplEmplTypeInfo;
import com.anubhav.springdatajpa.repositories.DepartmentRepo;

@Service
public class DepartmentService {
	
	private DepartmentRepo repo;

	public List<DeptEmplEmplTypeInfo> getInfo(Integer id){
		return Optional.ofNullable(id)
				.filter(deptId->deptId>0)
				.map(deptId->{
					return repo.getInfo(deptId);
				})
				.orElse(null);
	}
	
}
