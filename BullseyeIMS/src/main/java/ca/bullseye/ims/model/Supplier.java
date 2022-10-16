package ca.bullseye.ims.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * @author Ella Moldez
 *
 */

@Entity
@Table
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long supId;

	@NotBlank(message = "Supplier name is required.")
	private String supName;
	
	@NotBlank(message = "Supplier contact person is required.")
	private String supContactPerson;

	@NotBlank(message = "Supplier address is required.")
	private String supAddress;
	
	@NotBlank(message = "Supplier city name is required.")
	private String supCity;
	
	@NotBlank(message = "Supplier state or province is required.")
	private String supStateOrProvince;
	
	@NotBlank(message = "Supplier postal code is required.")
	private String supPostalCode;
	
	@NotBlank(message = "Supplier country is required.")
	private String supCountry;

	@NotBlank(message = "Supplier email is required.")
	@Email(regexp = ".+@.+\\..+", message = "Email did not match format - test@example.com")
	private String supEmail;

	@NotBlank(message = "Supplier contact number is required.")
	private String supContact;
	
	private String supNotes;
	
	@OneToMany(mappedBy = "supplier")
	private List<Order> orders;

	
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

	public String getSupContactPerson() {
		return supContactPerson;
	}

	public void setSupContactPerson(String supContactPerson) {
		this.supContactPerson = supContactPerson;
	}

	public String getSupAddress() {
		return supAddress;
	}

	public void setSupAddress(String supAddress) {
		this.supAddress = supAddress;
	}

	public String getSupCity() {
		return supCity;
	}

	public void setSupCity(String supCity) {
		this.supCity = supCity;
	}

	public String getSupStateOrProvince() {
		return supStateOrProvince;
	}

	public void setSupStateOrProvince(String supStateOrProvince) {
		this.supStateOrProvince = supStateOrProvince;
	}

	public String getSupPostalCode() {
		return supPostalCode;
	}

	public void setSupPostalCode(String supPostalCode) {
		this.supPostalCode = supPostalCode;
	}

	public String getSupCountry() {
		return supCountry;
	}

	public void setSupCountry(String supCountry) {
		this.supCountry = supCountry;
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

	public String getSupNotes() {
		return supNotes;
	}

	public void setSupNotes(String supNotes) {
		this.supNotes = supNotes;
	}
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	
	
}
