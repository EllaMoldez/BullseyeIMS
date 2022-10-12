package ca.bullseye.ims.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ca.bullseye.ims.exceptions.RecordNotFoundException;
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

	/*
	 * private static List<String> prodStatus; static { prodStatus = new
	 * ArrayList<>(); prodStatus.add("Continued"); prodStatus.add("Discontinued"); }
	 */

	@GetMapping(path = "/product")
	public String viewProductList(Model model) {
		List<Product> productList = productService.getAllProducts();
		model.addAttribute("productList", productList);

		return "productlist";
	}

	@RequestMapping("/product/add")
	public String newProduct(Model model) {
		Product product = new Product();
		model.addAttribute(product);

		model.addAttribute("prodCategories", prodCategories);
		/* model.addAttribute("prodStatus", prodStatus); */

		return "product_new";
	}

	@RequestMapping(value = "/product/save", method = RequestMethod.POST)
	public String saveProduct(@Valid Product products, BindingResult result, @ModelAttribute("product") Product product) {
		if(result.hasErrors()) {
			return "product_new";
		}
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
	@RequestMapping(value = "/product/edit/{prodId}",  method = RequestMethod.GET)
	public ModelAndView editProduct(@PathVariable(name = "prodId") Long prodId, @Valid Product products, BindingResult result) throws RecordNotFoundException {
		ModelAndView mav = new ModelAndView("product_edit");
		Product product = productService.getProductById(prodId);
		mav.addObject("product", product);
		return mav;
	}

	// creating a delete mapping that deletes a specific product
	@RequestMapping(value = "/product/delete/{prodId}")
	private String deleteProduct(@PathVariable(name = "prodId") Long prodId) {
		productService.deleteProduct(prodId);
		return "redirect:/product";
	}
}