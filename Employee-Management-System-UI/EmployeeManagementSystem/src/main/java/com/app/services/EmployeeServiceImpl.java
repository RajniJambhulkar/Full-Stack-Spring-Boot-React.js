package com.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.Employee;
import com.app.model.EmployeeModel;
import com.app.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public EmployeeModel createEmployee(EmployeeModel employee) {
		Employee emp = new Employee();
		BeanUtils.copyProperties(employee, emp);
		employeeRepository.save(emp);
		
		return employee;
	}

	@Override
	public List<EmployeeModel> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		
		List<EmployeeModel> empModel = employees.stream()
				.map(emp -> new EmployeeModel(
							emp.getId(), 
							emp.getFirstname(), 
							emp.getLastname(),
							emp.getEmailId()))
				.collect(Collectors.toList());
		return empModel;
	}

	@Override
	public boolean deleteEmployee(Long id) {
		Employee  employee = employeeRepository.findById(id).get();
		employeeRepository.delete(employee);
		return true;
	}

	@Override
	public EmployeeModel getEmployeeById(Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
		EmployeeModel employeeModel = new EmployeeModel();
		BeanUtils.copyProperties(employee, employeeModel);
		return employeeModel;
	}

	@Override
	public EmployeeModel updateEmployee(Long id, EmployeeModel employee) {
		Employee employeeEntity = employeeRepository.findById(id).get();
		employeeEntity.setEmailId(employee.getEmailId());
		employeeEntity.setFirstname(employee.getFirstname());
		employeeEntity.setLastname(employee.getLastname());
		employeeRepository.save(employeeEntity);
		return employee;
	}
	
	
	
}
