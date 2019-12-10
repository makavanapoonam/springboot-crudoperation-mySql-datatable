package com.employeemanage.employeemanage.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.employeemanage.employeemanage.entity.Employee;

@Controller
@RequestMapping(value = "/emp")
public interface EMployeeService {

	@GetMapping("/home")
	public String showHome();
	
	@GetMapping("/getdata")
	public ResponseEntity<?> showDataTable();
	
	@GetMapping("/add")
	public String addHome();
	
	@PostMapping(value = "/save")
	public @ResponseBody Map<String,Object> save(@RequestBody Employee employee);
	
	@PostMapping("edit/{empid}")
	public ResponseEntity<?> edit(@PathVariable("empid") String editId);
	
	@PostMapping("delete/{empid}")
	public ResponseEntity<?> delete(@PathVariable("empid") String editId);
}
