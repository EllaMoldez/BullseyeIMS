
package ca.bullseye.ims.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "orders")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	@NotBlank(message = "Order Id is required.")
	@Column(name="OID")
	private String oId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="ORDERCREATED")
	private Date orderCreated;

	@NotBlank(message = "Employee name is required.")
	@Column(name="ORDERBYEMPLOYEE")
	private String orderByEmployee;
	
	@NotBlank(message = "Product is required.")
	@Column(name="ORDEREDPRODUCT")
	private String orderedProduct;
	
	@NotNull(message = "Total product price must not be null.")
	@Digits(integer = 19, fraction = 2)
	@Column(name="TOTALPRICE")
	private BigDecimal totalPrice;
	
	@NotNull(message = "Quantity must not be null.")
	@Column(name="ORDERQUANTITY")
	private int orderQuantity;

	@NotNull(message = "Product price must not be null.")
	@Digits(integer = 19, fraction = 2)
	@Column(name="ORDERPRICE")
	private BigDecimal orderPrice;
	
	@NotBlank(message = "Order status is required.")
	@Column(name="ORDERSTATUS")
	private String orderStatus;
	
	@Column(name="ORDERNOTE")
	private String orderNote;

	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getoId() {
		return oId;
	}

	public void setoId(String oId) {
		this.oId = oId;
	}

	public Date getOrderCreated() {
		return orderCreated;
	}

	public void setOrderCreated(Date orderCreated) {
		this.orderCreated = orderCreated;
	}

	public String getOrderByEmployee() {
		return orderByEmployee;
	}

	public void setOrderByEmployee(String orderByEmployee) {
		this.orderByEmployee = orderByEmployee;
	}

	public String getOrderedProduct() {
		return orderedProduct;
	}

	public void setOrderedProduct(String orderedProduct) {
		this.orderedProduct = orderedProduct;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public String getOrderNote() {
		return orderNote;
	}

	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}

	
	/*
	 * @NotNull(message = "Employee must not be null")
	 * 
	 * @OneToMany
	 * 
	 * @JoinColumn(name = "empId") private List<Employee> employee;
	 * 
	 * @OneToMany
	 * 
	 * @JoinColumn(name = "prodId") private List<Product> product;
	 * 
	 * @OneToMany
	 * 
	 * @JoinColumn(name = "supId") private List<Supplier> supplier;
	 */
	

}
