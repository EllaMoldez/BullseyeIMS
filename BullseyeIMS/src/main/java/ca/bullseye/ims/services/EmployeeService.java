package ca.bullseye.ims.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import org.springframework.stereotype.Service;

import ca.bullseye.ims.model.Employee;

import ca.bullseye.ims.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
    
	/* Display all employee records */
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	/* Sort employee list */
	public List<Employee> getAllEmployees(Sort sort) {
		return employeeRepository.findAll();
	}
	
	/* Pagination */
	public Page<Employee> getAllEmployees(Pageable pageable) {
		return employeeRepository.findAll(pageable);
	}
	
	/* Find employee record by Id */
	public Optional<Employee> getEmployeeById(Long empId) {
		return employeeRepository.findById(empId);
	}
	
	public Page<Employee> getEmployeesBySearchValue(String value, Pageable pageable) {
		Employee employee = new Employee();
		employee.setEmpFirstName(value);
		employee.setEmpLastName(value);
		employee.setEmpDepartment(value);
		employee.setEmpJobRole(value);
		employee.setEmpEmail(value);
		employee.setEmpContact(value);
		employee.setEmpUserName(value);

		ExampleMatcher.GenericPropertyMatcher propertyMatcher = ExampleMatcher.GenericPropertyMatchers.contains()
				.ignoreCase();
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withMatcher("empFirstName", propertyMatcher)
				.withMatcher("empLastName", propertyMatcher).withMatcher("empDepartment", propertyMatcher)
				.withMatcher("empJobRole", propertyMatcher).withMatcher("empEmail", propertyMatcher).withMatcher("empContact", propertyMatcher)
				.withMatcher("empUserName", propertyMatcher);
		Example<Employee> employeeExample = Example.of(employee, exampleMatcher);
		return employeeRepository.findAll(employeeExample, pageable);
	}

	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public void deleteEmployee(Employee employee) {
		employeeRepository.delete(employee);
	}

	public void deleteEmployeeById(Long empId) {
		employeeRepository.deleteById(empId);
	}

	public boolean isEmployeeExists(Long empId) {
		return employeeRepository.existsById(empId);
	}

	public long getTotalCount() {
		return employeeRepository.count();
	}
	
	public boolean doesEmployeeExist(String empUserName) {
		return employeeRepository.existsByEmpUserName(empUserName);
	}
	
	public Employee findEmployee(String empUserName) {
		Optional<Employee> optional = Optional.of(employeeRepository.findByEmpUserName(empUserName));
		Employee employee = null;
		
		if(optional.isPresent()) {
			employee = optional.get();		
		}		
		return employeeRepository.findByEmpUserName(empUserName);
	}
	
}
