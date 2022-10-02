package ca.bullseye.ims.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="Employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int empId;
	
	private String empFirstName;
	
	private String empLastName;
	
	private int empContact;
	
	private String empEmail;
	
	private String empUsername;
	
	private String empPassword;
	
	@Transient
	private String empPasswordConfirm;
	
	@OneToOne
	private Set<Role> role;

	public Employee() {
	}

	public Employee(int empId, String empFirstName, String empLastName, int empContact, String empEmail,
			String empUsername, String empPassword, String empPasswordConfirm, Set<Role> role) {
		this.empId = empId;
		this.empFirstName = empFirstName;
		this.empLastName = empLastName;
		this.empContact = empContact;
		this.empEmail = empEmail;
		this.empUsername = empUsername;
		this.empPassword = empPassword;
		this.empPasswordConfirm = empPasswordConfirm;
		this.role = role;
	}

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

	public String getEmpPasswordConfirm() {
		return empPasswordConfirm;
	}

	public void setEmpPasswordConfirm(String empPasswordConfirm) {
		this.empPasswordConfirm = empPasswordConfirm;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
}

