package ca.bullseye.ims.controllers;

import java.util.ArrayList;
import java.util.Arrays;
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

	private static List<String> prodCategories;
	static {
		prodCategories = new ArrayList<>();
		prodCategories.add("Clothing");
		prodCategories.add("Footwear");
		prodCategories.add("Accessories");
		prodCategories.add("Equipment");
		prodCategories.add("Wellness");
		prodCategories.add("Seasonal");
	}

	private static List<String> prodStatus;
	static {
		prodStatus = new ArrayList<>();
		prodStatus.add("Continued");
		prodStatus.add("Discontinued");
	}

	@GetMapping(path = "/product")
	public String viewHomePage(Model model) {
		List<Product> productList = productService.getAllProducts();
		model.addAttribute("productList", productList);
		/*
		 * List<String> prodCategories = Arrays.asList("Clothing", "Footwear",
		 * "Accessories", "Equipment", "Wellness", "Seasonal");
		 * 
		 * model.addAttribute("prodCategories", prodCategories);
		 * 
		 * List<String> prodStatus = Arrays.asList("Continued", "Discontinued");
		 * model.addAttribute("prodStatus", prodStatus);
		 */

		return "productlist";
	}

	@RequestMapping("/product/add")
	public String newProduct(Model model) {
		Product product = new Product();
		model.addAttribute(product);
		
		model.addAttribute("prodCategories", prodCategories);
		model.addAttribute("prodStatus", prodStatus);
		
		return "product_new";
	}

	@RequestMapping(value = "/product/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
		productService.saveProduct(product);
		return "redirect:/product";
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
	@RequestMapping("/product/edit/{prodId}")
	public ModelAndView showEditProduct(@PathVariable(name = "prodId") Long prodId) throws ProductNotFoundException {
		ModelAndView mav = new ModelAndView("edit_product");
		Product product = productService.getProductById(prodId);
		mav.addObject("product", product);
		return mav;
	}

	// creating a delete mapping that deletes a specific product
	@RequestMapping("/product/delete/{prodId}")
	private String deleteProduct(@PathVariable(name = "prodId") Long prodId) {
		productService.deleteProduct(prodId);
		return "redirect:/product";
	}

	/*
	 * // creating post mapping that post the product detail in the database
	 * 
	 * @PostMapping("/product") private Long saveProduct(@RequestBody Product
	 * product) { productService.saveProduct(product); return product.getProdId(); }
	 */
}