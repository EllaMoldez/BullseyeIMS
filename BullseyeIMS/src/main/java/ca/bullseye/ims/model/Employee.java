
package ca.bullseye.ims.model;

import javax.persistence.*;

@Entity
@Table
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empId;

	private String empFirstName;

	private String empLastName;

	private int empContact;

	private String empEmail;

	private String empUsername;

	private String empPassword;
	
	/*
	 * @Transient private String empPasswordConfirm;
	 */
	
	public Employee() {
		super();
	}

	public Employee(Long empId, String empFirstName, String empLastName, int empContact, String empEmail,
			String empUsername, String empPassword) {
		super();
		this.empId = empId;
		this.empFirstName = empFirstName;
		this.empLastName = empLastName;
		this.empContact = empContact;
		this.empEmail = empEmail;
		this.empUsername = empUsername;
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

	

	

}
