package ca.bullseye.ims.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long prodId;

	private String prodSKU;

	private String prodName;

	private String prodCategory;

	private String prodDescription;

	private String prodColor;

	private BigDecimal price;

	private String prodStatus;

	public Product() {
		super();
	}

	public Product(Long prodId, String prodSKU, String prodName, String prodCategory, String prodDescription,
			String prodColor, BigDecimal price, String prodStatus) {
		super();
		this.prodId = prodId;
		this.prodSKU = prodSKU;
		this.prodName = prodName;
		this.prodCategory = prodCategory;
		this.prodDescription = prodDescription;
		this.prodColor = prodColor;
		this.price = price;
		this.prodStatus = prodStatus;
	}

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

	public String getProdDescription() {
		return prodDescription;
	}

	public void setProdDescription(String prodDescription) {
		this.prodDescription = prodDescription;
	}

	public String getProdColor() {
		return prodColor;
	}

	public void setProdColor(String prodColor) {
		this.prodColor = prodColor;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getProdStatus() {
		return prodStatus;
	}

	public void setProdStatus(String prodStatus) {
		this.prodStatus = prodStatus;
	}

}
