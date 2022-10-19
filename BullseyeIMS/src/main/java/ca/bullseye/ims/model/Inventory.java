package ca.bullseye.ims.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {

	@OneToMany
	@JoinColumn(name = "employee_empId")
	private List<Employee> employees;

	@OneToMany
	@JoinColumn(name = "product_prodId")
	private List<Product> products;

	@OneToMany
	@JoinColumn(name = "supplier_supId")
	private List<Supplier> suppliers;
	
//	@OneToMany
//	@JoinColumn(name = "order_orderId")
//	private List<Order> orders;
	
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

//	public List<Order> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(List<Order> orders) {
//		this.orders = orders;
//	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long inventoryId;
}