package ca.bullseye.ims.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Role")
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;
	
	private String roleName;
	
	@OneToOne(mappedBy = "role")
    private Set<Employee> employee;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(Set<Employee> employee) {
		this.employee = employee;
	}
	
	
}
