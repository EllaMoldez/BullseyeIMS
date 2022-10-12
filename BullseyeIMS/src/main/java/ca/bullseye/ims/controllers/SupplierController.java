
package ca.bullseye.ims.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ca.bullseye.ims.model.Supplier;
import ca.bullseye.ims.services.SupplierService;

@Controller
public class SupplierController {

	@Autowired
	private SupplierService supplierService;

	// display list of suppliers
	@GetMapping(path = "/supplier")
	public String viewSupplierList(Model model) {
		return findPaginated(1, "supName", "asc", model);
	}

	// show new supplier page
	@GetMapping(path = "/supplier/add")
	public String newSupplier(Model model) {
		// create model attribute to bind form data
		Supplier supplier = new Supplier();
		model.addAttribute(supplier);
		return "supplier_new";
	}

	// save supplier to database
	@RequestMapping(path = "/supplier/save", method = RequestMethod.POST)
	public String saveSupplier(@Valid Supplier sup, BindingResult result, Supplier supplier) {
		if(result.hasErrors()) {
			return "supplier_new";
		}
		supplierService.saveSupplier(supplier);
		return "redirect:/supplier";
	}

	@GetMapping(path = "/supplier/edit/{supId}")
	public String editSupplier(@PathVariable(value = "supId") Long supId, Model model) {
		// get supplier from the service
		Supplier supplier = supplierService.getSupplierById(supId);

		// set supplier as a model attribute to pre-populate the form
		model.addAttribute("supplier", supplier);
		return "/supplier_edit";
	}

	// deletes a specific supplier
	@GetMapping(path = "supplier/delete/{supId}")
	private String deleteSupplier(@PathVariable(name = "supId") Long supId) {
		// call delete employee method
		supplierService.deleteSupplier(supId);
		return "redirect:/supplier";
	}

	// pagination and sorting
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDirection") String sortDirection, Model model) {
		int pageSize = 6;

		Page<Supplier> page = supplierService.findPaginated(pageNo, pageSize, sortField, sortDirection);
		List<Supplier> supplierList = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

		model.addAttribute("supplierList", supplierList);
		return "supplierlist";
	}
}
