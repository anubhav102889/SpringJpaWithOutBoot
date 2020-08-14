package com.anubhav.springdatajpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.anubhav.springdatajpa.models.DeptEmplEmplTypeInfo;
import com.anubhav.springdatajpa.repositories.EntityManagerRespostiory;

@Service
public class EntityManagerService {
	
	@Autowired
	private EntityManagerRespostiory respostiory;
	
	public List<DeptEmplEmplTypeInfo> queryUsingCriteria(Integer deptId,Pageable pageable){
		return Optional.ofNullable(pageable)
				.filter(page->{
					return !Optional.ofNullable(page.getSort()).get().toString().equalsIgnoreCase("unsorted")
				&& page.getPageSize()>0;
				})
				.map(page->{
					List<DeptEmplEmplTypeInfo> list=respostiory.queryUsingCriteria(deptId, page);
					return list;
				})
				.orElse(null);
	}

}
