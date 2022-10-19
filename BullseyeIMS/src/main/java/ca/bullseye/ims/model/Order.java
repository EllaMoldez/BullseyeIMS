
package ca.bullseye.ims.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "order")
public class Order {

	@OneToMany
	@JoinColumn(name = "employee_empId")
	private List<Employee> employee;

	@OneToMany
	@JoinColumn(name = "product_prodId")
	private List<Product> product;

	@OneToMany
	@JoinColumn(name = "supplier_supId")
	private List<Supplier> supplier;
	
//	@OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
//	private List<Inventory> inventoryItems;

	public List<Employee> getEmployee() {
		return employee;
	}
	
	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	public List<Supplier> getSupplier() {
		return supplier;
	}

	public void setSupplier(List<Supplier> supplier) {
		this.supplier = supplier;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date orderCreated;

	
}
