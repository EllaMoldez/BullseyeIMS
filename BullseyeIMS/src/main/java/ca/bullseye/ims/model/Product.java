package ca.bullseye.ims.model;

import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "Product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long prodId;
	
	@NotEmpty(message = "Product SKU cannot be empty.")
	@Size(min=2, max=10)
	private String prodSKU;
	
	@NotEmpty(message = "Product name cannot be empty.")
	private String prodName;
	
	@NotEmpty(message = "Product category cannot be empty.")
	private String prodCategory;
	
	@NotEmpty(message = "Brand name cannot be empty.")
	private String prodBrand;

	private String prodDescription;
	
	@NotNull(message = "Product size cannot be empty.")
	private int prodSize;
	
	@NotEmpty(message = "Product color cannot be empty.")
	private String prodColor;
	
	@NotEmpty(message = "Product location cannot be empty.")
	private String prodLocation;
	
	@Digits(integer=8, fraction=2)
	private BigDecimal prodPrice;
	
	@NotEmpty(message = "Product status cannot be empty.")
	private String prodStatus;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "supId") private Supplier supplier;
	 */

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

	/*
	 * public Supplier getSupplier() { return supplier; }
	 * 
	 * public void setSupplier(Supplier supplier) { this.supplier = supplier; }
	 */

}
