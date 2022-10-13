package ca.bullseye.ims.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empId;
	
	@NotBlank(message = "Employee first name is required.")
	private String empFirstName;
	
	@NotBlank(message = "Employee last name is required.")
	private String empLastName;
	
	@NotBlank(message = "Employee email is required.")
	/*
	 * @Email(regexp = ".+@.+\\..+", message =
	 * "Email did not match format - test@example.com")
	 */
	@Email(regexp = "\"^(.+)@(.+)$\"", message = "Email did not match format - test@example.com")
	private String empEmail;
	
	@NotBlank(message = "Employee contact number is required.")
	private String empContact;
	
	@NotBlank(message = "Department is required.")
	private String empDepartment;
	
	@NotBlank(message = "Job role is required.")
	private String empJobRole;
	
	
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
	
}
