package com.anubhav.springdatajpa.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.anubhav.springdatajpa.models.DeptEmplEmplTypeInfo;
import com.anubhav.springdatajpa.services.DepartmentService;

@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentService service;
	
	@GetMapping(value = "/getInfo/{deptId}" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DeptEmplEmplTypeInfo>> getInfo(@PathVariable(value = "deptId")Integer id){
		return Optional.ofNullable(service.getInfo(id))
				.filter(list->!CollectionUtils.isEmpty(list))
				.map(list->{
					return new ResponseEntity<>(list, HttpStatus.OK);
				})
				.orElseGet(()->{
					return new ResponseEntity("no results found",HttpStatus.NOT_FOUND);
				});
	}

}
