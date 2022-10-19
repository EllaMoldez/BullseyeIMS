
package ca.bullseye.ims.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.bullseye.ims.model.Employee;
import ca.bullseye.ims.model.Order;
import ca.bullseye.ims.model.Product;
import ca.bullseye.ims.model.Supplier;
import ca.bullseye.ims.repositories.EmployeeRepository;
import ca.bullseye.ims.repositories.OrderRepository;
import ca.bullseye.ims.repositories.ProductRepository;
import ca.bullseye.ims.repositories.SupplierRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private SupplierRepository supplierRepository;

//	Display all
//	supplier records

	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	public List<Supplier> getAllSuppliers() {
		return supplierRepository.findAll();
	}

	public Optional<Order> getOrderById(Long orderId) {
		return orderRepository.findById(orderId);
	}

	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}

	public void deleteOrderById(Long orderId) {
		orderRepository.deleteById(orderId);
	}

	public boolean isOrderExists(Long orderId) {
		return orderRepository.existsById(orderId);
	}

	public long getTotalCount() {
		return orderRepository.count();
	}
}