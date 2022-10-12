package ca.bullseye.ims.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long supId;

	@NotBlank(message = "Supplier name is required.")
	@Size(min = 2, max = 250)
	private String supName;

	@NotBlank(message = "Supplier address is required.")
	private String supAddress;

	@NotBlank(message = "Supplier email is required.")
	@Email
	private String supEmail;

	@NotBlank(message = "Supplier contact number is required.")
	private String supContact;

	/*
	 * @OneToMany(mappedBy = "supplier") private Set <Product> product;
	 */

	public Long getSupId() {
		return supId;
	}

	public void setSupId(Long supId) {
		this.supId = supId;
	}

	public String getSupName() {
		return supName;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}

	public String getSupAddress() {
		return supAddress;
	}

	public void setSupAddress(String supAddress) {
		this.supAddress = supAddress;
	}

	public String getSupEmail() {
		return supEmail;
	}

	public void setSupEmail(String supEmail) {
		this.supEmail = supEmail;
	}

	public String getSupContact() {
		return supContact;
	}

	public void setSupContact(String supContact) {
		this.supContact = supContact;
	}
}
