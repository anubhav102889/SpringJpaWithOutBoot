package com.anubhav.springdatajpa.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anubhav.springdatajpa.models.DeptEmplEmplTypeInfo;
import com.anubhav.springdatajpa.services.EntityManagerService;

@RestController
public class EntityManagerController {

	@Autowired
	private EntityManagerService service;

	@GetMapping(value="/queryUsingCriteria",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DeptEmplEmplTypeInfo>> queryUsingCriteria(
			@RequestParam(value = "deptId", required = false) Integer deptId, Pageable pageable) {
		return Optional.ofNullable(service.queryUsingCriteria(deptId, pageable))
				.filter(list -> !CollectionUtils.isEmpty(list)).map(list -> {
					return new ResponseEntity<>(list, HttpStatus.OK);
				}).orElseGet(() -> {
					return new ResponseEntity("no results found", HttpStatus.NOT_FOUND);
				});

	}

}
