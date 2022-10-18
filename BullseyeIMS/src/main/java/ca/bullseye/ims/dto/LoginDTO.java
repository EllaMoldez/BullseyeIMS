package ca.bullseye.ims.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype") 
public class LoginDTO {
	private String empUserName;
	private String empPassword;
	
	public LoginDTO() {
		super();
	}

	public LoginDTO(String empUserName, String empPassword) {
		super();
		this.empUserName = empUserName;
		this.empPassword = empPassword;
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
}