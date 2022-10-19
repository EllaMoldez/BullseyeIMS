
package ca.bullseye.ims.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

import ca.bullseye.ims.model.Employee;
import ca.bullseye.ims.model.Orders;
import ca.bullseye.ims.model.Product;
import ca.bullseye.ims.services.EmployeeService;
import ca.bullseye.ims.services.OrderService;
import ca.bullseye.ims.services.ProductService;

@Controller
public class OrdersController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;

	private List<Orders> orderList;
	private List<Employee> employeeList;
	private List<Product> productList;

	@ModelAttribute("currentOrders")
	public Orders currentOrders() {
		return new Orders();
	}

	@ModelAttribute("orderList")
	public List<Orders> populateOrdersList() {
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

	@GetMapping("/order")
	public String getOrdersPage(@RequestParam(value = "search", required = false) String search,
			@RequestParam(defaultValue = "orderId") String sort, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "15") int size, @RequestParam(defaultValue = "asc") String direction,
			Model model, HttpServletRequest request) {
		if (request.getSession().getAttribute("loggedIn") != null) {
			Order sortOrders = new Sort.Order(Sort.Direction.fromString(direction), sort).ignoreCase();
			PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(sortOrders));

			Page<Orders> orderPage;
			if (search != null && !search.isBlank()) {
				orderPage = orderService.getOrdersBySearchValue(search, pageRequest);
			} else {
				orderPage = orderService.getAllOrders(pageRequest);
			}
			model.addAttribute("currentPage", page);
			model.addAttribute("pageSize", size);
			model.addAttribute("totalPages", orderPage.getTotalPages());
			model.addAttribute("order", orderPage.getContent());
			model.addAttribute("employeeList", employeeList);
			model.addAttribute("productList", productList);
			return "order";
		}
		return "login";
	}

	@GetMapping("/addOrder")
	public String getAddOrdersPage(Model model, HttpServletRequest request) {
		if (request.getSession().getAttribute("loggedIn") != null) {
			model.addAttribute("pageTitle", "Create Order");
			model.addAttribute("order", new Orders());

			return "order-edit";
		}
		return "login";
	}

	@GetMapping("/updateOrder")
	public String getUpdateOrdersPage(@RequestParam Long orderId, Model model, HttpServletRequest request) {
		if (request.getSession().getAttribute("loggedIn") != null) {
			Orders existingOrders = orderService.getOrderById(orderId)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Orders not found."));
			model.addAttribute("pageTitle", "Update Orders");
			model.addAttribute("order", existingOrders);
			model.addAttribute("employeeList", employeeList);
			model.addAttribute("productList", productList);
			return "order-edit";
		}
		return "login";
	}

	@PostMapping("/saveOrder")
	public String saveOrder(@Valid @ModelAttribute Orders order, Errors errors, Model model,
			HttpServletRequest request) {
		if (request.getSession().getAttribute("loggedIn") != null) {
			if (errors.hasErrors()) {
				model.addAttribute("pageTitle", (order.getOrderId() != null ? "Update" : "Create") + " Order");
				return "order-edit";
			} else {
				orderService.saveOrder(order);
				return "redirect:/order";
			}
		}
		return "login";
	}

	@GetMapping("/deleteOrder")
	public String deleteOrder(@RequestParam Long orderId, Model model, HttpServletRequest request) {
		if (request.getSession().getAttribute("loggedIn") != null) {
			if (!orderService.isOrderExists(orderId)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found.");
			}

			this.orderService.deleteOrderById(orderId);
			return "redirect:/order";
		}
		return "login";
	}

}
