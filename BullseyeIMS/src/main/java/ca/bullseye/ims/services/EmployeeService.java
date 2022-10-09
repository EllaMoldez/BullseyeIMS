package ca.bullseye.ims.services;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import ca.bullseye.ims.model.Employee;
import ca.bullseye.ims.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	// display all employee records
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	// create new or update employee details
	public void saveProduct(Employee employee) {
		employeeRepository.save(employee);
	}
	
	// deleting a specific record
		public void deleteEmployee(Long empId) {
			employeeRepository.deleteById(empId);
		}

}
