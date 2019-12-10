package com.employeemanage.employeemanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanage.employeemanage.entity.Employee;

public interface EmployeeMasterRepository extends JpaRepository<Employee,Integer>{

}
