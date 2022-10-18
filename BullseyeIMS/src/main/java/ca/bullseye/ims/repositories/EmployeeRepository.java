package ca.bullseye.ims.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.bullseye.ims.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
