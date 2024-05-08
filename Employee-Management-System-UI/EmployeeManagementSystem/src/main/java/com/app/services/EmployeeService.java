package com.app.services;

import java.util.List;

import com.app.entities.Employee;
import com.app.model.EmployeeModel;


public interface EmployeeService {

	EmployeeModel createEmployee(EmployeeModel employee);
		
	List<EmployeeModel> getAllEmployees();
	
	boolean deleteEmployee(Long id);
	
	EmployeeModel getEmployeeById(Long id);
		
	EmployeeModel updateEmployee(Long id, EmployeeModel employee);
}
