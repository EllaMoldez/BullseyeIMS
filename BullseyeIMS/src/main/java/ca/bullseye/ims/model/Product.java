package ca.bullseye.ims.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long prodId;
	
	@NotBlank(message = "Product SKU is required.")
	@Size(min=2, max=10)
	private String prodSKU;
	
	@NotBlank(message = "Product name is required.")
	private String prodName;
	
	@NotBlank(message = "Product category is required.")
	private String prodCategory;
	
	@NotEmpty(message = "Brand name is required.")
	private String prodBrand;

	private String prodDescription;
	
	@NotNull(message = "Product size is required.")
	private int prodSize;
	
	@NotBlank(message = "Product color is required.")
	private String prodColor;
	
	@NotBlank(message = "Product location is required.")
	private String prodLocation;
	
	@Digits(integer=8, fraction=2)
	@NotNull(message = "Product price is required.")
	private BigDecimal prodPrice;
	
	@NotBlank(message = "Product status is required.")
	private String prodStatus;
	
	@OneToMany(mappedBy = "product")
	private List<Order> orders;

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

	public int getProdSize() {
		return prodSize;
	}

	public void setProdSize(int prodSize) {
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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	
}
