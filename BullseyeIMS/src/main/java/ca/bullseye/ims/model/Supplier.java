package ca.bullseye.ims.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * @author Ella Moldez
 *
 */

@Entity
@Table(name = "supplier")
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long supId;

	@NotBlank(message = "Supplier name is required.")
	@Column(name="SUPNAME")
	private String supName;
	
	@NotBlank(message = "Supplier contact person is required.")
	@Column(name="SUPCONTACTPERSON")
	private String supContactPerson;

	@NotBlank(message = "Supplier address is required.")
	@Column(name="SUPADDRESS")
	private String supAddress;
	
	@NotBlank(message = "Supplier city name is required.")
	@Column(name="SUPCITY")
	private String supCity;
	
	@NotBlank(message = "Supplier state or province is required.")
	@Column(name="SUPSTATEORPROVINCE")
	private String supStateOrProvince;
	
	@NotBlank(message = "Supplier postal code is required.")
	@Column(name="SUPPOSTALCODE")
	private String supPostalCode;
	
	@NotBlank(message = "Supplier country is required.")
	@Column(name="SUPCOUNTRY")
	private String supCountry;

	@NotBlank(message = "Supplier email is required.")
	@Email(regexp = ".+@.+\\..+", message = "Email did not match format - test@example.com")
	@Column(name="SUPEMAIL")
	private String supEmail;

	@NotBlank(message = "Supplier contact number is required.")
	@Column(name="SUPCONTACT")
	private String supContact;
	
	@Column(name="SUPNOTES")
	private String supNotes;
	
	@OneToMany(mappedBy = "suppliers", fetch = FetchType.LAZY)
	private List<Inventory> inventoryItems;
	
//	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
//	private List<Order> orderItems;
	
	public List<Inventory> getInventoryItems() {
		return inventoryItems;
	}

	public void setInventoryItems(List<Inventory> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}

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
	
	
	
}
