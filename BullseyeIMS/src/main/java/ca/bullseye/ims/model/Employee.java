package ca.bullseye.ims.model;

import javax.persistence.*;

@Entity
@Table(name = "Employee")
public class Employee {
	@Id
	@GeneratedValue
	private int empId;
	private String empFirstName;
	private String empLastName;
	private String username;
	private String password;
	
}
