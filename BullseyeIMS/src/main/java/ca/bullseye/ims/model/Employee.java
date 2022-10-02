package ca.bullseye.ims.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="Employee")
public class Employee {
	@Id
	@GeneratedValue
	private int empId;
	private String empFirstName;
	private String empLastName;
	private int empContact;
	private String empEmail;
	private String empUsername;
	private String empPassword;
	
	@ManyToMany
	private Set<Role> roles;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
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

	public int getEmpContact() {
		return empContact;
	}

	public void setEmpContact(int empContact) {
		this.empContact = empContact;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getEmpUsername() {
		return empUsername;
	}

	public void setEmpUsername(String empUsername) {
		this.empUsername = empUsername;
	}

	public String getEmpPassword() {
		return empPassword;
	}

	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
		
}

