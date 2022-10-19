
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	/*
	 * @GetMapping("/order") // request show add order page public String
	 * addOrders(Model model, HttpServletRequest request) { if
	 * (request.getSession().getAttribute("loggedIn") != null) { List<Employee>
	 * employeeList = employeeService.getAllEmployees();
	 * model.addAttribute("employeeList", employeeList);
	 * model.addAttribute("productList", productList); return "order"; } return
	 * "login"; }
	 */

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

	/*
	 *
	 * 
	 * START OF THE OTHER CODE
	 * 
	 * @GetMapping("/order")
	 * 
	 * public String getOrderssPage(@RequestParam(value = "oId", required = false)
	 * Long oId,
	 * 
	 * @RequestParam(value = "search", required = false) String search,
	 * 
	 * @RequestParam(defaultValue = "orderCreated") String
	 * sort, @RequestParam(defaultValue = "1") int page,
	 * 
	 * @RequestParam(defaultValue = "15") int size, @RequestParam(defaultValue =
	 * "asc") String direction, Model model) {
	 * 
	 * if (oId != null) { Orders order = this.orderService.getOrdersById(oId)
	 * .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
	 * "Orders not found.")); model.addAttribute("order", order); return
	 * "order-detail"; }
	 * 
	 * if (sort.equalsIgnoreCase("employee")) { sort = "employee.empFirstName"; }
	 * 
	 * Sort.Orders sortOrders = new
	 * Sort.Orders(Sort.Direction.fromString(direction), sort).ignoreCase();
	 * PageRequest pageRequest = PageRequest.of(page - 1, size,
	 * Sort.by(sortOrders));
	 * 
	 * Page<Orders> orderPage; if (search != null && !search.isBlank()) { orderPage
	 * = orderService.getOrderssBySearchValue(search, pageRequest); } else {
	 * orderPage = orderService.getAllOrderss(pageRequest); }
	 * 
	 * List<Orders> orders = orderPage.getContent();
	 * 
	 * Optional<BigDecimal> totalOrderssPrice = orders.stream().flatMap(order ->
	 * order.getOrdersItems().stream())
	 * .map(OrdersItem::getTotalPrice).reduce(BigDecimal::add);
	 * 
	 * model.addAttribute("currentPage", page); model.addAttribute("pageSize",
	 * size); model.addAttribute("totalPages", orderPage.getTotalPages());
	 * 
	 * model.addAttribute("totalOrderssPrice",
	 * totalOrderssPrice.orElse(BigDecimal.ZERO));
	 * 
	 * model.addAttribute("orders", orderPage.getContent()); return "order"; }
	 * 
	 * @GetMapping("/createOrders") public String
	 * getCreateOrdersPage(@RequestParam(value = "empId") Long empId,
	 * 
	 * @RequestParam(value = "search", required = false) String search,
	 * 
	 * @RequestParam(defaultValue = "empFirstName") String
	 * sort, @RequestParam(defaultValue = "1") int page,
	 * 
	 * @RequestParam(defaultValue = "15") int size, @RequestParam(defaultValue =
	 * "asc") String direction,
	 * 
	 * @ModelAttribute("currentOrders") Orders order, Model model) { if
	 * (order.getEmployee() == null ||
	 * !order.getEmployee().getEmpId().equals(empId)) { Employee employee =
	 * employeeService.getEmployeeById(empId) .orElseThrow(() -> new
	 * ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found."));
	 * order.setOrdersId(null); order.setoId(null); order.setEmployee(employee); }
	 * 
	 * ArrayList<OrdersItem> orderItems = new ArrayList<>(); OrdersItem orderItem =
	 * new OrdersItem(); orderItem.setProduct(new Product());
	 * orderItems.add(orderItem); order.setOrdersItems(orderItems);
	 * 
	 * Sort.Orders sortOrders = new
	 * Sort.Orders(Sort.Direction.fromString(direction), sort).ignoreCase();
	 * PageRequest pageRequest = PageRequest.of(page - 1, size,
	 * Sort.by(sortOrders));
	 * 
	 * Page<Product> productPage; if (search != null && !search.isBlank()) {
	 * productPage = productService.getProductsBySearchValue(search, pageRequest); }
	 * else { productPage = productService.getAllProducts(pageRequest); }
	 * 
	 * model.addAttribute("currentPage", page); model.addAttribute("pageSize",
	 * size); model.addAttribute("totalPages", productPage.getTotalPages());
	 * model.addAttribute("product", productPage.getContent()); return
	 * "order-create"; }
	 * 
	 * @PostMapping("/saveCart") public String
	 * saveCart(@Valid @ModelAttribute("currentOrders") Orders order, Errors errors,
	 * SessionStatus sessionStatus, Model model) { if (errors.hasErrors()) { return
	 * "order-cart"; } else { recalculateOrders(order);
	 * 
	 * if (order.getOrdersItems().size() < 1) { return "order-cart"; }
	 * 
	 * Orders savedOrders = this.orderService.saveOrders(order);
	 * sessionStatus.setComplete(); model.addAttribute("order", savedOrders);
	 * 
	 * return "order-result"; } }
	 * 
	 * @GetMapping("/deleteOrders") public String
	 * deleteOrders(@RequestParam("orderId") Long orderId,
	 * 
	 * @RequestParam(defaultValue = "/order") String redirectUrl, Model model) { if
	 * (!orderService.isOrdersExists(orderId)) { throw new
	 * ResponseStatusException(HttpStatus.NOT_FOUND, "Orders not found."); }
	 * 
	 * Object order = model.getAttribute("currentOrders"); String
	 * clearSessionEmployeeId = "";
	 * 
	 * if (order != null) { Orders currentOrders = (Orders) order; Long
	 * currentOrdersId = currentOrders.getOrdersId(); if (currentOrdersId != null &&
	 * currentOrdersId.equals(orderId)) { if (currentOrders.getEmployee() != null &&
	 * currentOrders.getEmployee().getEmpId() != null) { clearSessionEmployeeId =
	 * (redirectUrl.contains("?") ? "&" : "?") + "clearEmployeeId=" +
	 * currentOrders.getEmployee().getEmpId(); model.addAttribute("currentOrders",
	 * null); } } }
	 * 
	 * orderService.deleteOrdersById(orderId); return "redirect:" + redirectUrl +
	 * clearSessionEmployeeId; }
	 * 
	 * @GetMapping("/updateOrders") public String
	 * getUpdateOrdersPage(@ModelAttribute("currentOrders") Orders
	 * order, @RequestParam("orderId") Long orderId, Model model) { Orders
	 * existingOrders = this.orderService.getOrdersById(orderId) .orElseThrow(() ->
	 * new ResponseStatusException(HttpStatus.NOT_FOUND, "Orders not found."));
	 * order.setOrdersId(existingOrders.getOrdersId());
	 * order.setoId(existingOrders.getoId());
	 * order.setEmployee(existingOrders.getEmployee());
	 * order.setOrdersItems(existingOrders.getOrdersItems());
	 * order.setOrdersCreated(existingOrders.getOrdersCreated());
	 * 
	 * model.addAttribute("currentOrders", order); return "redirect:/showCart"; }
	 * 
	 * @PostMapping("/showCart") public String
	 * getShowCartPage(@ModelAttribute("currentOrders") Orders order) { if
	 * (order.getEmployee() == null) { return "redirect:/"; }
	 * 
	 * this.recalculateOrders(order);
	 * 
	 * return "order-cart"; }
	 * 
	 * @GetMapping("/showCart") public String
	 * getShowCartPage(@ModelAttribute("currentOrders") Orders order,
	 * 
	 * @RequestParam(required = false) Long deleteId) { if (order.getEmployee() ==
	 * null) { return "redirect:/"; }
	 * 
	 * if (deleteId != null) { List<OrdersItem> orderItems = order.getOrdersItems();
	 * orderItems.removeIf(item -> item.getProduct().getProdId().equals(deleteId));
	 * } return "order-cart"; }
	 * 
	 * private void recalculateOrders(Orders order) { List<OrdersItem> orderItems =
	 * order.getOrdersItems(); orderItems.removeIf( item -> item.getProduct() ==
	 * null || item.getProduct().getProdId() == null || item.getAmount() < 1);
	 * Supplier<ResponseStatusException> exceptionSupplier = () -> new
	 * ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
	 * 
	 * for (OrdersItem orderItem : orderItems) { Long productId =
	 * orderItem.getProduct().getProdId(); Product dbProduct =
	 * productService.getProductById(productId).orElseThrow(exceptionSupplier);
	 * orderItem.setProduct(dbProduct);
	 * orderItem.setPrice(dbProduct.getProdPrice());
	 * orderItem.setTotalPrice(dbProduct.getProdPrice().multiply(BigDecimal.valueOf(
	 * orderItem.getAmount()))); orderItem.setOrders(order); } }
	 */
}
