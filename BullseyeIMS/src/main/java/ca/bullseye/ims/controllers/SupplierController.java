
package ca.bullseye.ims.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import ca.bullseye.ims.model.Supplier;
import ca.bullseye.ims.services.SupplierService;

@Controller
public class SupplierController {

	@Autowired
	private SupplierService supplierService;

	@GetMapping("/supplier")
	public String getSupplierPage(@RequestParam(value = "supId", required = false) Long supId,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(defaultValue = "supName") String sort, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "15") int size, @RequestParam(defaultValue = "asc") String direction,
			Model model, HttpServletRequest request) {
		if (request.getSession().getAttribute("loggedIn") != null) {
			if (supId != null) {
				Supplier supplier = supplierService.getSupplierById(supId)
						.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found"));
				model.addAttribute("supplier", supplier);
				return "supplier-info";
			}

			Sort.Order sortOrder = new Sort.Order(Sort.Direction.fromString(direction), sort).ignoreCase();
			PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(sortOrder));

			Page<Supplier> supplierPage;
			if (search != null && !search.isBlank()) {
				supplierPage = supplierService.getSuppliersBySearchValue(search, pageRequest);
			} else {
				supplierPage = supplierService.getAllSuppliers(pageRequest);
			}
			model.addAttribute("currentPage", page);
			model.addAttribute("pageSize", size);
			model.addAttribute("totalPages", supplierPage.getTotalPages());
			model.addAttribute("supplier", supplierPage.getContent());
			return "supplier";
		}
		return "login";

	}

	@GetMapping("/addSupplier")
	public String getAddSupplierPage(Model model, HttpServletRequest request) {
		if (request.getSession().getAttribute("loggedIn") != null) {
			model.addAttribute("pageTitle", "Create Supplier");
			model.addAttribute("supplier", new Supplier());
			return "supplier-edit";
		}
		return "login";
	}

	@GetMapping("/updateSupplier")
	public String getUpdateSupplierPage(@RequestParam Long supId, Model model, HttpServletRequest request) {
		if (request.getSession().getAttribute("loggedIn") != null) {
			Supplier existingSupplier = this.supplierService.getSupplierById(supId)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found."));
			model.addAttribute("pageTitle", "Edit Supplier");
			model.addAttribute("supplier", existingSupplier);
			return "supplier-edit";
		}
		return "login";
	}

	@PostMapping("/saveSupplier")
	public String saveSupplier(@Valid @ModelAttribute Supplier supplier, Errors errors, Model model,
			HttpServletRequest request) {
		if (request.getSession().getAttribute("loggedIn") != null) {
			if (errors.hasErrors()) {
				model.addAttribute("pageTitle", (supplier.getSupId() != null ? "Update" : "Create") + " Supplier");
				return "supplier-edit";
			} else {
				supplierService.saveSupplier(supplier);
				return "redirect:/supplier";
			}
		}
		return "login";
	}

	@GetMapping("/deleteSupplier")
	public String deleteSupplier(@RequestParam Long supId, Model model, HttpServletRequest request) {
		if (request.getSession().getAttribute("loggedIn") != null) {
			if (!supplierService.isSupplierExists(supId)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found.");
			}

			supplierService.deleteSupplierById(supId);
			return "redirect:/supplier";
		}
		return "login";
	}

}
