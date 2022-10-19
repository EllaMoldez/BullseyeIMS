
package ca.bullseye.ims.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import ca.bullseye.ims.model.Employee;
import ca.bullseye.ims.model.Order;
import ca.bullseye.ims.model.Product;
import ca.bullseye.ims.services.OrderService;
import ca.bullseye.ims.services.ProductService;

@Controller
public class OrderController {

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;

	private List<Employee> employeeList;
	private List<Product> productList;

	@ModelAttribute("currentOrder")
	public Order currentOrder() {
		return new Order();
	}

//	@RequestMapping(value = "/order", method = RequestMethod.GET) // request show
//
//	public String addOrder(@ModelAttribute("Order") Order order, Map<String, Object> model) {
//		model.put("employeeList", employeeList);
//		model.put("productList", productList);
//		return "order";
//	}

	@GetMapping("/order")
	public String getInventoryPage(Model model, HttpServletRequest request) {
		if (request.getSession().getAttribute("loggedIn") != null) {
			List<Order> orderList = orderService.getAllOrders();
			model.addAttribute("orderList", orderList);
			return "order";
		}
		return "login";
	
	}

	@GetMapping("/createOrder")
	public String getCreateOrderPage(@RequestParam(value = "empId") Long empId,

			@RequestParam(value = "search", required = false) String search,

			@RequestParam(defaultValue = "empFirstName") String sort, @RequestParam(defaultValue = "1") int page,

			@RequestParam(defaultValue = "15") int size, @RequestParam(defaultValue = "asc") String direction,

			@ModelAttribute("currentOrder") Order order, Model model) {

		Sort.Order sortOrder = new Sort.Order(Sort.Direction.fromString(direction), sort).ignoreCase();
		PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(sortOrder));

		Page<Product> productPage;
		if (search != null && !search.isBlank()) {
			productPage = productService.getProductsBySearchValue(search, pageRequest);
		} else {
			productPage = productService.getAllProducts(pageRequest);
		}

		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", size);
		model.addAttribute("totalPages", productPage.getTotalPages());
		model.addAttribute("product", productPage.getContent());
		return "order-create";
	}

}
