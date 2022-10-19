package ca.bullseye.ims.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

@Entity
@Table(name = "inventory")
public class Inventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long inventoryId;

	@Column(name="prodSKU")
	private String prodSKU;

	@Column(name="prodName")
	private String prodName;
	
	@Column(name="prodCategory")
	private String prodCategory;
	
	@Digits(integer = 19, fraction = 2)
	@Column(name="prodQuantity")
	private BigDecimal prodQuantity;
	
	@Column(name="prodSupplier")
	private String prodSupplier;
	
	@Digits(integer = 19, fraction = 2)
	@Column(name="prodPrice")
	private BigDecimal prodPrice;
	
	@Column(name="prodStatus")
	private String prodStatus;
	
	@Column(name="INVENTORYTOTALPRICE")
	private String inventoryTotalPrice;
	
	@Column(name="INVENTORYIDEALQUANTITY")
	private String inventoryIdealQuantity;
	
	@Column(name="QUANTITYAVAILABLE")
	private String quantityAvailable;
	
	@Column(name="INVENTORY STATUS")
	private String inventoryStatus;

	public Long getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Long inventoryId) {
		this.inventoryId = inventoryId;
	}

	public String getProdSKU() {
		return prodSKU;
	}

	public void setProdSKU(String prodSKU) {
		this.prodSKU = prodSKU;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdCategory() {
		return prodCategory;
	}

	public void setProdCategory(String prodCategory) {
		this.prodCategory = prodCategory;
	}

	public @Digits(integer = 19, fraction = 2) BigDecimal getProdQuantity() {
		return prodQuantity;
	}

	public void setProdQuantity(@Digits(integer = 19, fraction = 2) BigDecimal prodQuantity) {
		this.prodQuantity = prodQuantity;
	}

	public String getProdSupplier() {
		return prodSupplier;
	}

	public void setProdSupplier(String prodSupplier) {
		this.prodSupplier = prodSupplier;
	}

	public @Digits(integer = 19, fraction = 2) BigDecimal getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(@Digits(integer = 19, fraction = 2) BigDecimal prodPrice) {
		this.prodPrice = prodPrice;
	}

	public String getProdStatus() {
		return prodStatus;
	}

	public void setProdStatus(String prodStatus) {
		this.prodStatus = prodStatus;
	}
}