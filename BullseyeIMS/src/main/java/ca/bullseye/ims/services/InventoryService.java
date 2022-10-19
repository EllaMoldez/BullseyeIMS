package ca.bullseye.ims.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.bullseye.ims.model.Employee;
import ca.bullseye.ims.model.Inventory;
import ca.bullseye.ims.model.Product;
import ca.bullseye.ims.model.Supplier;
import ca.bullseye.ims.repositories.EmployeeRepository;
import ca.bullseye.ims.repositories.InventoryRepository;
import ca.bullseye.ims.repositories.ProductRepository;
import ca.bullseye.ims.repositories.SupplierRepository;

@Service
public class InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	/* Display all product records */
	public List<Inventory> getAllInventories() {
		return inventoryRepository.findAll();
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
}
