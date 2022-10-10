package ca.bullseye.ims.services;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import ca.bullseye.ims.exceptions.ProductNotFoundException;
import ca.bullseye.ims.model.Employee;
import ca.bullseye.ims.model.Product;
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
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	// getting a specific employee record
	public Employee getEmployeeById(Long empId) throws ProductNotFoundException {
		if (employeeRepository.findById((Long) empId).isPresent()) {
			return employeeRepository.findById((Long) empId).get();
		} else if (employeeRepository.findById((Long) empId).isEmpty()) {
			throw new ProductNotFoundException("" + empId);
		}
		return null;
	}

	// deleting a specific record
	public void deleteEmployee(Long empId) {
		employeeRepository.deleteById(empId);
	}

}
