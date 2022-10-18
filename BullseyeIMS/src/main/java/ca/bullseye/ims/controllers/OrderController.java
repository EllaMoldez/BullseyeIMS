/*
 * package ca.bullseye.ims.controllers;
 * 
 * import java.math.BigDecimal; import java.util.ArrayList; import
 * java.util.List; import java.util.Map; import java.util.Optional; import
 * java.util.function.Supplier;
 * 
 * import javax.validation.Valid;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.data.domain.Page; import
 * org.springframework.data.domain.PageRequest; import
 * org.springframework.data.domain.Sort; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import org.springframework.validation.Errors;
 * import org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.ModelAttribute; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.bind.support.SessionStatus; import
 * org.springframework.web.server.ResponseStatusException;
 * 
 * import ca.bullseye.ims.model.Employee; import ca.bullseye.ims.model.Order;
 * import ca.bullseye.ims.model.OrderItem; import ca.bullseye.ims.model.Product;
 * import ca.bullseye.ims.services.EmployeeService; import
 * ca.bullseye.ims.services.OrderService; import
 * ca.bullseye.ims.services.ProductService;
 * 
 * @Controller public class OrderController {
 * 
 * @Autowired private EmployeeService employeeService;
 * 
 * @Autowired private ProductService productService;
 * 
 * @Autowired private OrderService orderService;
 * 
 * private List<Order> orderList; private List<Employee> employeeList; private
 * List<Product> productList;
 * 
 * @ModelAttribute("currentOrder") public Order currentOrder() { return new
 * Order(); }
 * 
 * @RequestMapping(value = "/order", method = RequestMethod.GET) // request show
 * add order page public String addOrder(@ModelAttribute("Order") Order order,
 * Map<String, Object> model) { model.put("employeeList", employeeList);
 * model.put("productList", productList); return "order"; }
 * 
 * 
 * 
 * 
 * START OF THE OTHER CODE
 * 
 * @GetMapping("/order") public String getOrdersPage(@RequestParam(value =
 * "oId", required = false) Long oId,
 * 
 * @RequestParam(value = "search", required = false) String search,
 * 
 * @RequestParam(defaultValue = "orderCreated") String
 * sort, @RequestParam(defaultValue = "1") int page,
 * 
 * @RequestParam(defaultValue = "15") int size, @RequestParam(defaultValue =
 * "asc") String direction, Model model) {
 * 
 * if (oId != null) { Order order = this.orderService.getOrderById(oId)
 * .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
 * "Order not found.")); model.addAttribute("order", order); return
 * "order-detail"; }
 * 
 * if (sort.equalsIgnoreCase("employee")) { sort = "employee.empFirstName"; }
 * 
 * Sort.Order sortOrder = new Sort.Order(Sort.Direction.fromString(direction),
 * sort).ignoreCase(); PageRequest pageRequest = PageRequest.of(page - 1, size,
 * Sort.by(sortOrder));
 * 
 * Page<Order> orderPage; if (search != null && !search.isBlank()) { orderPage =
 * orderService.getOrdersBySearchValue(search, pageRequest); } else { orderPage
 * = orderService.getAllOrders(pageRequest); }
 * 
 * List<Order> orders = orderPage.getContent();
 * 
 * Optional<BigDecimal> totalOrdersPrice = orders.stream().flatMap(order ->
 * order.getOrderItems().stream())
 * .map(OrderItem::getTotalPrice).reduce(BigDecimal::add);
 * 
 * model.addAttribute("currentPage", page); model.addAttribute("pageSize",
 * size); model.addAttribute("totalPages", orderPage.getTotalPages());
 * 
 * model.addAttribute("totalOrdersPrice",
 * totalOrdersPrice.orElse(BigDecimal.ZERO));
 * 
 * model.addAttribute("orders", orderPage.getContent()); return "order"; }
 * 
 * @GetMapping("/createOrder") public String
 * getCreateOrderPage(@RequestParam(value = "empId") Long empId,
 * 
 * @RequestParam(value = "search", required = false) String search,
 * 
 * @RequestParam(defaultValue = "empFirstName") String
 * sort, @RequestParam(defaultValue = "1") int page,
 * 
 * @RequestParam(defaultValue = "15") int size, @RequestParam(defaultValue =
 * "asc") String direction,
 * 
 * @ModelAttribute("currentOrder") Order order, Model model) { if
 * (order.getEmployee() == null ||
 * !order.getEmployee().getEmpId().equals(empId)) { Employee employee =
 * employeeService.getEmployeeById(empId) .orElseThrow(() -> new
 * ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found."));
 * order.setOrderId(null); order.setoId(null); order.setEmployee(employee); }
 * 
 * ArrayList<OrderItem> orderItems = new ArrayList<>(); OrderItem orderItem =
 * new OrderItem(); orderItem.setProduct(new Product());
 * orderItems.add(orderItem); order.setOrderItems(orderItems);
 * 
 * Sort.Order sortOrder = new Sort.Order(Sort.Direction.fromString(direction),
 * sort).ignoreCase(); PageRequest pageRequest = PageRequest.of(page - 1, size,
 * Sort.by(sortOrder));
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
 * saveCart(@Valid @ModelAttribute("currentOrder") Order order, Errors errors,
 * SessionStatus sessionStatus, Model model) { if (errors.hasErrors()) { return
 * "order-cart"; } else { recalculateOrder(order);
 * 
 * if (order.getOrderItems().size() < 1) { return "order-cart"; }
 * 
 * Order savedOrder = this.orderService.saveOrder(order);
 * sessionStatus.setComplete(); model.addAttribute("order", savedOrder);
 * 
 * return "order-result"; } }
 * 
 * @GetMapping("/deleteOrder") public String
 * deleteOrder(@RequestParam("orderId") Long orderId,
 * 
 * @RequestParam(defaultValue = "/order") String redirectUrl, Model model) { if
 * (!orderService.isOrderExists(orderId)) { throw new
 * ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found."); }
 * 
 * Object order = model.getAttribute("currentOrder"); String
 * clearSessionEmployeeId = "";
 * 
 * if (order != null) { Order currentOrder = (Order) order; Long currentOrderId
 * = currentOrder.getOrderId(); if (currentOrderId != null &&
 * currentOrderId.equals(orderId)) { if (currentOrder.getEmployee() != null &&
 * currentOrder.getEmployee().getEmpId() != null) { clearSessionEmployeeId =
 * (redirectUrl.contains("?") ? "&" : "?") + "clearEmployeeId=" +
 * currentOrder.getEmployee().getEmpId(); model.addAttribute("currentOrder",
 * null); } } }
 * 
 * orderService.deleteOrderById(orderId); return "redirect:" + redirectUrl +
 * clearSessionEmployeeId; }
 * 
 * @GetMapping("/updateOrder") public String
 * getUpdateOrderPage(@ModelAttribute("currentOrder") Order
 * order, @RequestParam("orderId") Long orderId, Model model) { Order
 * existingOrder = this.orderService.getOrderById(orderId) .orElseThrow(() ->
 * new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found."));
 * order.setOrderId(existingOrder.getOrderId());
 * order.setoId(existingOrder.getoId());
 * order.setEmployee(existingOrder.getEmployee());
 * order.setOrderItems(existingOrder.getOrderItems());
 * order.setOrderCreated(existingOrder.getOrderCreated());
 * 
 * model.addAttribute("currentOrder", order); return "redirect:/showCart"; }
 * 
 * @PostMapping("/showCart") public String
 * getShowCartPage(@ModelAttribute("currentOrder") Order order) { if
 * (order.getEmployee() == null) { return "redirect:/"; }
 * 
 * this.recalculateOrder(order);
 * 
 * return "order-cart"; }
 * 
 * @GetMapping("/showCart") public String
 * getShowCartPage(@ModelAttribute("currentOrder") Order order,
 * 
 * @RequestParam(required = false) Long deleteId) { if (order.getEmployee() ==
 * null) { return "redirect:/"; }
 * 
 * if (deleteId != null) { List<OrderItem> orderItems = order.getOrderItems();
 * orderItems.removeIf(item -> item.getProduct().getProdId().equals(deleteId));
 * } return "order-cart"; }
 * 
 * private void recalculateOrder(Order order) { List<OrderItem> orderItems =
 * order.getOrderItems(); orderItems.removeIf( item -> item.getProduct() == null
 * || item.getProduct().getProdId() == null || item.getAmount() < 1);
 * Supplier<ResponseStatusException> exceptionSupplier = () -> new
 * ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
 * 
 * for (OrderItem orderItem : orderItems) { Long productId =
 * orderItem.getProduct().getProdId(); Product dbProduct =
 * productService.getProductById(productId).orElseThrow(exceptionSupplier);
 * orderItem.setProduct(dbProduct);
 * orderItem.setPrice(dbProduct.getProdPrice());
 * orderItem.setTotalPrice(dbProduct.getProdPrice().multiply(BigDecimal.valueOf(
 * orderItem.getAmount()))); orderItem.setOrder(order); } }
 * 
 * }
 */