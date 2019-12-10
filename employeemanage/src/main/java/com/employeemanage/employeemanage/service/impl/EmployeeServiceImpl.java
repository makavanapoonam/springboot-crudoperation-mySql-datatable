package com.employeemanage.employeemanage.service.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.employeemanage.employeemanage.datatable.config.DataTableConfig;
import com.employeemanage.employeemanage.entity.Employee;
import com.employeemanage.employeemanage.repository.EmployeeMasterRepository;
import com.employeemanage.employeemanage.service.EMployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmployeeServiceImpl implements EMployeeService{
	
	@Autowired
	HttpServletRequest request;
	
	ObjectMapper objectMapper;
	
	@Autowired
	public EmployeeMasterRepository employeeRep;
	
	@Autowired
	private EntityManagerFactory entityMangeerFactory;
	
	@Autowired
	private ApplicationContext context;

	@Override
	public String showHome() {
		return "home";
	}

	@Override
	public String addHome() {
		return "add";
	}

	@Override
	public Map<String, Object> save(Employee employee) {
		try
		{
			EntityManager entityManager = null;
			EntityTransaction entityTransaction = null;
			
			Employee employeeMaster = null;
			
			final int id = (null == employee.getEmpid()) ? -1
					: employee.getEmpid();
			
			entityManager = entityMangeerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			
			if(id != 0 && id != -1)
			{
				employeeMaster = entityManager.find(Employee.class, id);
				employeeMaster.setEmpname(employee.getEmpname());
				employeeMaster.setCity(employee.getCity());
				employeeMaster.setAddress(employee.getAddress());
				employeeMaster.setMobile(employee.getMobile());
			}
			else
			{
				employeeMaster = context.getBean(Employee.class);
				employeeMaster.setEmpname(employee.getEmpname());
				employeeMaster.setCity(employee.getCity());
				employeeMaster.setAddress(employee.getAddress());
				employeeMaster.setMobile(employee.getMobile());
			}
			entityManager.merge(employeeMaster);
			entityTransaction.commit();
			entityManager.clear();
			entityManager.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseEntity<?> showDataTable() {
		try
		{
			Map<String, String[]> rMap = request.getParameterMap();
			for(Entry<String, String[]> entry: rMap.entrySet()) {
				System.out.println(entry.getKey() + " => " + entry.getValue()[0]);
			}
			return new ResponseEntity<>(getPaginatedData(request), HttpStatus.OK);
			
		/*	String sqlQuery = " SELECT stateid,statename,stategstcode,region,isactive FROM statemaster";
			String whereClause = "where 1=1";
			String countQuery = " SELECT COUNT(*) FROM STATEMASTER";
			return new ResponseEntity<Object>(
					objectMapper.writeValueAsString(request, sqlQuery, countQuery, whereClause, "statemaster",
							new String[] { "stateid", "statename","stategstcode","region","isactive"}),
					HttpStatus.OK);*/
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public Map<String, Object> getPaginatedData(HttpServletRequest request) {

		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		entityManager = entityMangeerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Map<String, Object> pageMap = new HashMap<>();
		DataTableConfig dataTableConfig = new DataTableConfig(request);
		String mainQuery = "select empid,empname,city,address,mobile from employee";
		
		String whereClause = "WHERE empid = ?1 OR empname LIKE CONCAT('%', ?1, '%') OR city LIKE CONCAT('%', ?1, '%')";
		
		String countQuery = "SELECT COUNT(*) FROM employee";
		
		String orderBy = "ORDER BY " + dataTableConfig.getColumnName() + " " + dataTableConfig.getSortDir();
		
		
		
		Session session = entityManager.unwrap(Session.class);
		
		Long recordsTotal = ((BigInteger)session.createNativeQuery(countQuery).getSingleResult()).longValue();
		Long recordsFiltered;
		List<?> empList = null;
		
		//Query query = null;
		
		if (dataTableConfig.getSearch().trim().length() > 0 && dataTableConfig.getSearch() != null && !"".equals(dataTableConfig.getSearch())) {
			mainQuery += " " + whereClause + " " + orderBy;
			countQuery += " " + whereClause;
		
			empList = session.createNativeQuery(mainQuery).setParameter(1, dataTableConfig.getSearch()).setFirstResult(dataTableConfig.getStart().intValue()).setMaxResults(dataTableConfig.getLength().intValue()).getResultList();
			recordsFiltered = ((BigInteger)session.createNativeQuery(countQuery).setParameter(1, dataTableConfig.getSearch()).getSingleResult()).longValue();
		} else {
			empList = session.createNativeQuery(mainQuery + " " + orderBy).setFirstResult(dataTableConfig.getStart().intValue()).setMaxResults(dataTableConfig.getLength().intValue()).getResultList();
			recordsFiltered = ((BigInteger)session.createNativeQuery(countQuery).getSingleResult()).longValue();
		}
		
		
		
		
		pageMap.put("recordsTotal", recordsTotal);
		pageMap.put("recordsFiltered", recordsFiltered);
		pageMap.put("empList", empList);
		return pageMap;
	}

	@Override
	public ResponseEntity<?> edit(String editId) {
		try
		{
			if (null != editId) {
				Optional<Employee> crudData = employeeRep.findById(Integer.parseInt(editId));
				Map<String,Object> editDataMap = new HashMap<>();
				editDataMap.put("employeeData", crudData);
				return new ResponseEntity<Object>(editDataMap, HttpStatus.OK);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseEntity<?> delete(String editId) {
		try
		{
			if (null != editId) {
				employeeRep.deleteById(Integer.parseInt(editId));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
