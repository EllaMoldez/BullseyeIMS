package ca.bullseye.ims.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ca.bullseye.ims.exceptions.ProductNotFoundException;
import ca.bullseye.ims.model.Product;
import ca.bullseye.ims.services.ProductService;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;

	private static List<String> categories;
	static {
		categories = new ArrayList<>();
		categories.add("Clothing");
		categories.add("Footwear");
		categories.add("Accessories");
		categories.add("Equipment");
		categories.add("Wellness");
		categories.add("Seasonal");
	}

	private static List<String> status;
	static {
		status = new ArrayList<>();
		status.add("Continued");
		status.add("Discontinued");
	}

	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Product> productList = productService.getAllProducts();
		model.addAttribute("productList", productList);
		return "index";
	}

	@RequestMapping("/new")
	public String newProduct(Model model) {
		Product product = new Product();
		model.addAttribute(product);
		return "product_new";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
		productService.saveProduct(product);
		return "redirect:/";
	}

	/*
	 * // creating a get mapping that retrieves all the products from the database
	 * 
	 * @RequestMapping("/product") public List<Product> getAllProduct() { return
	 * productService.getAllProducts(); }
	 */

	// creating a get mapping that retrieves the detail of a specific product
	/*
	 * @RequestMapping("edit/{sid}") private Product
	 * getProductById(@PathVariable("id") Long id, Product product) { Product
	 * prodToView = null; try { prodToView = productService.getProductById(id); }
	 * catch (ProductNotFoundException e) { System.out.println(e);
	 * e.printStackTrace(); }
	 * 
	 * return prodToView; }
	 */
	@RequestMapping("edit/{prodId}")
	public ModelAndView showEditProduct(@PathVariable(name = "prodId") Long prodId) throws ProductNotFoundException {
		ModelAndView mav = new ModelAndView("edit_product");
		Product product = productService.getProductById(prodId);
		mav.addObject("product", product);
		return mav;
	}

	/*
	 * // creating post mapping that post the product detail in the database
	 * 
	 * @PostMapping("/product") private Long saveProduct(@RequestBody Product
	 * product) { productService.saveProduct(product); return product.getProdId(); }
	 */

	// creating a delete mapping that deletes a specific product
	@RequestMapping("delete/{prodId}")
	private String deleteProduct(@PathVariable(name = "prodId") Long prodId) {
		productService.deleteProduct(prodId);
		return "redirect:/";

	}
}