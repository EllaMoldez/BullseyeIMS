package ca.bullseye.ims.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.bullseye.ims.model.Employee;
import ca.bullseye.ims.repositories.EmployeeRepository;

@Service
public class LoginService {
	@Autowired
	private EmployeeRepository employeeRepository;
	

	public boolean doesEmployeeExist(String empUserName) {
		if(findEmployee(empUserName) != null) {
			return true;
		}
		return false;
	}
	
	public Employee findEmployee(String empUserName) {
		return employeeRepository.findByEmpUserName(empUserName);
	}



}