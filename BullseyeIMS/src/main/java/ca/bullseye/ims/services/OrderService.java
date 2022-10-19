
package ca.bullseye.ims.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ca.bullseye.ims.model.Employee;

import ca.bullseye.ims.model.Orders;
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

	public List<Orders> getAllOrders() {
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

	public List<Orders> getAllOrders(Sort sort) {
		return orderRepository.findAll(sort);
	}

	/* Pagination */

	public Page<Orders> getAllOrders(Pageable pageable) {
		return orderRepository.findAll(pageable);
	}

	public Page<Orders> getOrdersBySearchValue(String value, Pageable pageable) {
		Orders order = new Orders();
		order.setoId(value);
		Employee employee = new Employee();
		employee.setEmpFirstName(value);
		employee.setEmpLastName(value);

		ExampleMatcher.GenericPropertyMatcher propertyMatcher = ExampleMatcher.GenericPropertyMatchers.contains()
				.ignoreCase();
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withMatcher("oId", propertyMatcher)
				.withMatcher("employee.setEmpFirstName", propertyMatcher)
				.withMatcher("employee.setEmpLastName", propertyMatcher)
				.withMatcher("employee.setEmpDepartment", propertyMatcher);
		Example<Orders> orderExample = Example.of(order, exampleMatcher);
		return orderRepository.findAll(orderExample, pageable);
	}

	public Optional<Orders> getOrderById(Long orderId) {
		return orderRepository.findById(orderId);
	}

	public Orders saveOrder(Orders order) {
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
