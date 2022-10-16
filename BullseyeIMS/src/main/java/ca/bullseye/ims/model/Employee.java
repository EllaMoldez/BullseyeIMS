package ca.bullseye.ims.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
	@Email(regexp = ".+@.+\\..+", message = "Email did not match format - test@example.com")
	/*
	 * @Email(regexp = "\"^(.+)@(.+)$\"", message =
	 * "Email did not match format - test@example.com")
	 */
	private String empEmail;

	@NotBlank(message = "Employee contact number is required.")
	private String empContact;

	@NotBlank(message = "Department is required.")
	private String empDepartment;

	@NotBlank(message = "Job role is required.")
	private String empJobRole;
	
	@NotBlank(message = "Username is required.")
	@Size(min=6, max=10)
	private String empUserName;
	
	@NotBlank(message = "Password is required.")
	@Size(min=8, max=12, message = "Your password must between 8 and 15 characters")
	private String empPassword;

	@OneToMany(mappedBy = "employee")
	private List<Order> orders;

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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
}
