package ca.bullseye.ims.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import ca.bullseye.ims.model.Employee;
import ca.bullseye.ims.model.Inventory;
import ca.bullseye.ims.model.Orders;
import ca.bullseye.ims.model.Product;
import ca.bullseye.ims.model.Supplier;
import ca.bullseye.ims.services.EmployeeService;
import ca.bullseye.ims.services.InventoryService;
import ca.bullseye.ims.services.OrderService;
import ca.bullseye.ims.services.ProductService;
import ca.bullseye.ims.services.SupplierService;

@Controller
public class InventoryController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private SupplierService supplierService;

	private List<Employee> employeeList;
	private List<Product> productList;
	private List<Orders> orderList;
	private List<Supplier> supplierList;

	@ModelAttribute("currentInventories")
	public Inventory currentInventories() {
		return new Inventory();
	}
	
	@ModelAttribute("orderList")
	public List<Orders> populateOrderList() {
		orderList = orderService.getAllOrders();
		return orderList;
	}

	@ModelAttribute("productList")
	public List<Product> populateProductList() {
		productList = productService.getAllProducts();
		return productList;
	}

	@ModelAttribute("employeeList")
	public List<Employee> populateEmployeeList() {
		employeeList = employeeService.getAllEmployees();
		return employeeList;
	}
	
	@ModelAttribute("supplierList")
	public List<Supplier> populateSupplierList() {
		supplierList = supplierService.getAllSuppliers();
		return supplierList;
	}

	@GetMapping("/inventory")
	public String getInventoryPage(@RequestParam(value = "search", required = false) String search,
									@RequestParam(defaultValue = "inventoryId") String sort, 
									@RequestParam(defaultValue = "1") int page,
									@RequestParam(defaultValue = "15") int size, 
									@RequestParam(defaultValue = "asc") String direction,
									Model model, HttpServletRequest request) {
		if (request.getSession().getAttribute("loggedIn") != null) {

			model.addAttribute("currentPage", page);
			model.addAttribute("pageSize", size);
			model.addAttribute("employeeList", employeeList);
			model.addAttribute("productList", productList);
			model.addAttribute("orderList", orderList);
			model.addAttribute("supplierList", supplierList);
		
			return "inventory";
		}
		return "login";
	}
}
