package ca.bullseye.ims.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empId;

	@NotBlank(message = "Employee first name is required.")
	@Column(name = "EMPFIRSTNAME")
	private String empFirstName;

	@NotBlank(message = "Employee last name is required.")
	@Column(name = "EMPLASTNAME")
	private String empLastName;

	@NotBlank(message = "Employee email is required.")
	@Email(regexp = ".+@.+\\..+", message = "Email did not match format - test@example.com")
	@Column(name = "EMPEMAIL")
	private String empEmail;

	@NotBlank(message = "Employee contact number is required.")
	@Column(name = "EMPCONTACT")
	private String empContact;

	@NotBlank(message = "Department is required.")
	@Column(name = "EMPDEPARTMENT")
	private String empDepartment;

	@NotBlank(message = "Job role is required.")
	@Column(name = "EMPJOBROLE")
	private String empJobRole;

	@NotBlank(message = "Username is required.")
	@Size(min = 6, max = 15)
	@Column(name = "EMPUSERNAME")
	private String empUserName;

	@NotBlank(message = "Password is required.")
	@Size(min = 8, max = 12, message = "Your password must between 8 and 15 characters")
	@Column(name = "EMPPASSWORD")
	private String empPassword;

	@NotBlank(message = "Status is required.")
	@Column(name = "EMPSTATUS")
	private String empStatus;
	
	public Employee() {
		super();
	}

	public Employee(@NotBlank(message = "Username is required.") @Size(min = 6, max = 10) String empUserName,
			@NotBlank(message = "Password is required.") @Size(min = 8, max = 12, message = "Your password must between 8 and 15 characters") String empPassword) {
		super();
		this.empUserName = empUserName;
		this.empPassword = empPassword;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getEmpFirstName() {
		return empFirstName;
	}

	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}

	public String getEmpLastName() {
		return empLastName;
	}

	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getEmpContact() {
		return empContact;
	}

	public void setEmpContact(String empContact) {
		this.empContact = empContact;
	}

	public String getEmpDepartment() {
		return empDepartment;
	}

	public void setEmpDepartment(String empDepartment) {
		this.empDepartment = empDepartment;
	}

	public String getEmpJobRole() {
		return empJobRole;
	}

	public void setEmpJobRole(String empJobRole) {
		this.empJobRole = empJobRole;
	}

	public String getEmpUserName() {
		return empUserName;
	}

	public void setEmpUserName(String empUserName) {
		this.empUserName = empUserName;
	}

	public String getEmpPassword() {
		return empPassword;
	}

	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}

	public String getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}

}
