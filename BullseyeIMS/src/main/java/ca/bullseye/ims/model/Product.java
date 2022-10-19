package ca.bullseye.ims.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long prodId;

	@NotBlank(message = "Product SKU is required.")
	@Column(name = "PRODSKU")
	private String prodSKU;

	@NotBlank(message = "Product name is required.")
	@Column(name = "PRODNAME")
	private String prodName;

	@NotBlank(message = "Product category is required.")
	@Column(name = "PRODCATEGORY")
	private String prodCategory;

	@NotEmpty(message = "Brand name is required.")
	@Column(name = "PRODBRAND")
	private String prodBrand;

	@Column(name = "PRODDESCRIPTION")
	private String prodDescription;

	@NotNull(message = "Product size is required.")
	@Column(name = "PRODSIZE")
	private String prodSize;

	@NotBlank(message = "Product color is required.")
	@Column(name = "PRODCOLOR")
	private String prodColor;

	@NotBlank(message = "Product location is required.")
	@Column(name = "PRODLOCATION")
	private String prodLocation;

	@Digits(integer = 8, fraction = 2)
	@NotNull(message = "Product price is required.")
	@Column(name = "PRODPRICE")
	private BigDecimal prodPrice;

	@NotBlank(message = "Product status is required.")
	@Column(name = "PRODSTATUS")
	private String prodStatus;

	@OneToMany(mappedBy = "products", fetch = FetchType.LAZY)
	private List<Inventory> inventoryItems;
	
//	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
//	private List<Order> orderItems;

//	@PreRemove
//    public void preRemove() {
//        this.orderItems.forEach(orderItem -> orderItem.setProduct(null));
//    }

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
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

	public String getProdBrand() {
		return prodBrand;
	}

	public void setProdBrand(String prodBrand) {
		this.prodBrand = prodBrand;
	}

	public String getProdDescription() {
		return prodDescription;
	}

	public void setProdDescription(String prodDescription) {
		this.prodDescription = prodDescription;
	}

	public String getProdSize() {
		return prodSize;
	}

	public void setProdSize(String prodSize) {
		this.prodSize = prodSize;
	}

	public String getProdColor() {
		return prodColor;
	}

	public void setProdColor(String prodColor) {
		this.prodColor = prodColor;
	}

	public String getProdLocation() {
		return prodLocation;
	}

	public void setProdLocation(String prodLocation) {
		this.prodLocation = prodLocation;
	}

	public BigDecimal getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(BigDecimal prodPrice) {
		this.prodPrice = prodPrice;
	}

	public String getProdStatus() {
		return prodStatus;
	}

	public void setProdStatus(String prodStatus) {
		this.prodStatus = prodStatus;
	}

	public List<Inventory> getInventoryItems() {
		return inventoryItems;
	}

	public void setInventoryItems(List<Inventory> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}
	 

}
