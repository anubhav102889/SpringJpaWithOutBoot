package com.anubhav.springdatajpa.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;

import com.anubhav.springdatajpa.entities.Department;
import com.anubhav.springdatajpa.entities.Employee;
import com.anubhav.springdatajpa.entities.EmployeeType;
import com.anubhav.springdatajpa.models.DeptEmplEmplTypeInfo;

@Repository
public class EntityManagerRespostiory {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<DeptEmplEmplTypeInfo> queryUsingCriteria(Integer deptId,Pageable pageable){
		CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
		CriteriaQuery<DeptEmplEmplTypeInfo> criteriaQuery=criteriaBuilder.createQuery(DeptEmplEmplTypeInfo.class);
		Root<Department> deptRoot=criteriaQuery.from(Department.class);
		Join<Department,Employee> deptEmplJoin=deptRoot.join("empList");
		Join<Employee,EmployeeType> emplEmpTypeJoin=deptEmplJoin.join("employeeType");
		criteriaQuery.select(criteriaBuilder.construct(DeptEmplEmplTypeInfo.class, deptRoot.get("deptId"),
				deptRoot.get("deptName"),deptEmplJoin.get("empId"),
				deptEmplJoin.get("empName"),deptEmplJoin.get("empMail"),
				deptEmplJoin.get("empGender"),deptEmplJoin.get("empDoj")
				,emplEmpTypeJoin.get("empTypeId"),emplEmpTypeJoin.get("empTypeName")));
		List<Predicate> predicateList=new ArrayList<>();
		if(Objects.nonNull(deptId) && deptId>0) {
			Predicate deptCondition=criteriaBuilder.equal(deptRoot.get("deptId"),deptId);
		predicateList.add(deptCondition);
			
		}
		criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
		criteriaQuery.groupBy(deptRoot.get("deptId"),
				deptRoot.get("deptName"),deptEmplJoin.get("empId"),
				deptEmplJoin.get("empName"),deptEmplJoin.get("empMail"),
				deptEmplJoin.get("empGender"),deptEmplJoin.get("empDoj")
				,emplEmpTypeJoin.get("empTypeId"),emplEmpTypeJoin.get("empTypeName"));
		
		criteriaQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), deptEmplJoin, criteriaBuilder));
	TypedQuery<DeptEmplEmplTypeInfo> query=entityManager.createQuery(criteriaQuery);
	query.setFirstResult(pageable.getPageNumber());
	query.setMaxResults(pageable.getPageSize());
	List<DeptEmplEmplTypeInfo> list=query.getResultList();
	return list;
		
		
	}

}
