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
	private ProductService productService;

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

	// display list of products
	@RequestMapping(value = "/product")
	public String viewProductList(Model model) {
		List<Product> productList = productService.getAllProducts();
		model.addAttribute("productList", productList);

		return "productlist";
	}

	// show new or create product page
	@RequestMapping(value = "/product/create", method = RequestMethod.GET)
	public String newProduct(Model model) {
		/* create model attribute to bind form data */
		Product product = new Product();
		model.addAttribute(product);
		model.addAttribute("prodCategories", prodCategories);
		return "product_new";
	}

	// save new product to database
	@RequestMapping(value = "/product/add", method = RequestMethod.POST)
	public String addNewProduct(@ModelAttribute @Valid Product product, BindingResult result) {
		if (result.hasErrors()) {
			return "product_new";
		}
		productService.addNewProduct(product);
		return "redirect:/product";
	}
	
	// creating a get mapping that retrieves the detail of a specific product
	@GetMapping(path = "/product/edit/{prodId}")
	public String editProduct(@PathVariable(name = "prodId") Long prodId, Model model) {
		//get product from the service
		Product product = productService.getProductById(prodId);
		
		//set product as a model attribute to pre-populate the form
		model.addAttribute("product", product);
		model.addAttribute("prodCategories", prodCategories);
		return "/product_edit";
	}
	
	//save updated product record
	@RequestMapping(value = "/product/update", method = RequestMethod.POST)
	public String updateProduct(@ModelAttribute @Valid Product product, BindingResult result) {
		if(result.hasErrors()) {
			return "product_edit";
		}
		productService.updateProduct(product);
		return "redirect:/product";
	}

	// creating a delete mapping that deletes a specific product
	@RequestMapping(value = "/product/delete/{prodId}", method = { RequestMethod.DELETE, RequestMethod.GET })
	private String deleteProduct(@PathVariable(name = "prodId") Long prodId) {
		productService.deleteProduct(prodId);
		return "redirect:/product";
	}
}