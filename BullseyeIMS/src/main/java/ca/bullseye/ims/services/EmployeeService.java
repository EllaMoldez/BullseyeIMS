package ca.bullseye.ims.services;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ca.bullseye.ims.exceptions.RecordNotFoundException;
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
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	// getting a specific employee record
	public Employee getEmployeeById(Long empId) throws RecordNotFoundException {
		if (employeeRepository.findById((Long) empId).isPresent()) {
			return employeeRepository.findById((Long) empId).get();
		} else if (employeeRepository.findById((Long) empId).isEmpty()) {
			throw new RecordNotFoundException("" + empId);
		}
		return null;
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
