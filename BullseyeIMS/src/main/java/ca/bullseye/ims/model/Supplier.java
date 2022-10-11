package ca.bullseye.ims.model;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long supId;

	private String supName;

	private String supAddress;
	
	@Email
	private String supEmail;

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

	/*
	 * public Set<Product> getProduct() { return product; }
	 * 
	 * public void setProduct(Set<Product> product) { this.product = product; }
	 */

	

	
}
