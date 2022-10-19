package ca.bullseye.ims.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ca.bullseye.ims.model.Employee;
import ca.bullseye.ims.model.Inventory;
import ca.bullseye.ims.model.Product;
import ca.bullseye.ims.model.Supplier;
import ca.bullseye.ims.services.InventoryService;

@Controller
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;

	@GetMapping("/inventory")
	public String getInventoryPage(Model model, HttpServletRequest request) {
		if (request.getSession().getAttribute("loggedIn") != null) {
			List<Inventory> inventoryList = inventoryService.getAllInventories();
//			List<Product> productList = inventoryService.getAllProducts();
//			List<Employee> employeeList = inventoryService.getAllEmployees();
//			List<Supplier> supplierList = inventoryService.getAllSuppliers();
			model.addAttribute("inventoryList", inventoryList);
//			model.addAttribute("productList", productList);
//			model.addAttribute("employeeList", employeeList);
//			model.addAttribute("supplierList", supplierList);
			return "inventory";
		}
		return "login";
	}
}
