package com.craftEmployee.service.Controller;

import com.craftEmployee.service.RepoServeice.EmployeeService;
import com.craftEmployee.service.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController

@RequestMapping("/employees")
public class Controller {

	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/")
	
	public Employee saveEmployee(@RequestBody Employee employee) {
		
		
		return employeeService.saveEmployee(employee); 
		
	}
	
	@GetMapping("/{id}")
	
	@CircuitBreaker(name = "employeecircuitbreaker",fallbackMethod = "employeefallbackMethod")
	public ResponseEntity<?> getdepartmentDestails(@PathVariable ("id") Long id) {
		
		
		return new ResponseEntity<Object>(employeeService.reponse(id),HttpStatus.OK) ;
	}
	
	
	public ResponseEntity<?> employeefallbackMethod(Long id,Exception e){
		
		
		return new ResponseEntity<String>("department service not responding please try later",HttpStatus.OK);
		
	}
	
	
}


