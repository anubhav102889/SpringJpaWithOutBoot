package com.anubhav.springdatajpa.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.anubhav.springdatajpa.models.DeptEmplEmplTypeInfo;
import com.anubhav.springdatajpa.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired 
	private EmployeeRepository repository;
	
	public List<DeptEmplEmplTypeInfo> getInfo(Pageable p){
		return Optional.ofNullable(p)
				.filter(page->{
					return page.getPageSize()>0
							&& !Optional.ofNullable(page.getSort()).get().toString().equalsIgnoreCase("unsorted");
				})
				.map(page->{
					List<DeptEmplEmplTypeInfo> list=new ArrayList<>();
					List<Object []> objects=repository.getInfo(page);
					objects.stream().forEach(object->{
						DeptEmplEmplTypeInfo deptEmplEmplTypeInfo=new DeptEmplEmplTypeInfo();
						deptEmplEmplTypeInfo.setDeptId((int) object[0]);
						deptEmplEmplTypeInfo.setDeptName((String) object[1]);
						deptEmplEmplTypeInfo.setEmpId((int) object[2]);
						deptEmplEmplTypeInfo.setEmpName((String) object[3]);
						deptEmplEmplTypeInfo.setEmpMail((String) object[4]);
						deptEmplEmplTypeInfo.setEmpGender((String) object[5]);
						deptEmplEmplTypeInfo.setEmpDoj((Date) object[6]);
						deptEmplEmplTypeInfo.setEmpTypeId((int) object[7]);
						deptEmplEmplTypeInfo.setEmpTypeName((String) object[8]);
						deptEmplEmplTypeInfo.setIsContractor((String) object[9]);
						list.add(deptEmplEmplTypeInfo);
					});
					return list;
					
				})
				.orElse(null);
	}
}
