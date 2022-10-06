package ca.bullseye.ims.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.bullseye.ims.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
