package ca.bullseye.ims.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	// create new employee record
	public void addNewEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	// getting a specific employee record
	public Employee getEmployeeById(Long empId) {
		Optional<Employee> optional = employeeRepository.findById(empId);
		Employee employee = null;
		
		if(optional.isPresent()) {
			employee = optional.get();
		}else {
			throw new RuntimeException("Record not found for Employee Id: " + empId);
		}
		return employee;
	}
	
	// update employee record
	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
	
	// deleting a specific employee
	public void deleteEmployee(Long empId) {
		employeeRepository.deleteById(empId);
	}

	// pagination and sorting
	public Page<Employee> findPaginated(int pageNo, int pageSize, String sortFieldEmp, String sortDirectionEmp) {
		Sort sortEmp = sortDirectionEmp.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortFieldEmp).ascending()
				: Sort.by(sortFieldEmp).descending();
		Pageable pageableEmp = PageRequest.of(pageNo - 1, pageSize, sortEmp);
		return this.employeeRepository.findAll(pageableEmp);
	}

}
