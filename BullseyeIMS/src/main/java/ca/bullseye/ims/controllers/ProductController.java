package ca.bullseye.ims.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.*;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.*;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

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

	@GetMapping("/product")
	public String getProductsPage(@RequestParam(value = "search", required = false) String search,
			@RequestParam(defaultValue = "prodName") String sort, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "15") int size, @RequestParam(defaultValue = "asc") String direction,
			Model model, HttpServletRequest request) {
		if (request.getSession().getAttribute("loggedIn") != null) {
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
			model.addAttribute("prodCategories", prodCategories);
			return "product";
		}
		return "login";
	}

	@GetMapping("/addProduct")
	public String getAddProductPage(Model model, HttpServletRequest request) {
		if (request.getSession().getAttribute("loggedIn") != null) {
			model.addAttribute("pageTitle", "Create Product");
			model.addAttribute("product", new Product());
			model.addAttribute("prodCategories", prodCategories);
			return "product-edit";
		}
		return "login";
	}

	@GetMapping("/updateProduct")
	public String getUpdateProductPage(@RequestParam Long prodId, Model model, HttpServletRequest request) {
		if (request.getSession().getAttribute("loggedIn") != null) {
			Product existingProduct = productService.getProductById(prodId)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));
			model.addAttribute("pageTitle", "Update Product");
			model.addAttribute("product", existingProduct);
			model.addAttribute("prodCategories", prodCategories);
			return "product-edit";
		}
		return "login";
	}

	@PostMapping("/saveProduct")
	public String saveProduct(@Valid @ModelAttribute Product product, Errors errors, Model model,
			HttpServletRequest request) {
		if (request.getSession().getAttribute("loggedIn") != null) {
			if (errors.hasErrors()) {
				model.addAttribute("pageTitle", (product.getProdId() != null ? "Update" : "Create") + " Product");
				model.addAttribute("prodCategories", prodCategories);
				return "product-edit";
			} else {
				productService.saveProduct(product);
				return "redirect:/product";
			}
		}
		return "login";
	}

	@GetMapping("/deleteProduct")
	public String deleteProduct(@RequestParam Long prodId, Model model, HttpServletRequest request) {
		if (request.getSession().getAttribute("loggedIn") != null) {
			if (!productService.isProductExists(prodId)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
			}

			this.productService.deleteProductById(prodId);
			return "redirect:/product";
		}
		return "login";
	}

}