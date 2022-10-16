
package ca.bullseye.ims.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ca.bullseye.ims.model.Supplier;
import ca.bullseye.ims.services.SupplierService;

@Controller
public class SupplierController {

	@Autowired
	private SupplierService supplierService;

	// display list of suppliers
	@RequestMapping(value = "/supplier")
	public String viewSupplierList(Model model) {
		return findPaginated(1, "supId", "asc", model);
	}

	// show new or create supplier page
	@RequestMapping(value = "/supplier/create", method = RequestMethod.GET)
	public String newSupplier(Model model) { //
		/* create model attribute to bind form data */
		Supplier supplier = new Supplier();
		model.addAttribute(supplier);
		return "supplier_new";
	}

	// save new supplier to database
	@RequestMapping(value = "/supplier/add", method = RequestMethod.POST)
	public String addNewSupplier(@ModelAttribute @Valid Supplier supplier, BindingResult result) {
		if (result.hasErrors()) {
			return "supplier_new";
		}
		supplierService.addNewSupplier(supplier);
		return "redirect:/supplier";
	}

	// edit existing supplier record
	@GetMapping(path = "/supplier/edit/{supId}")
	public String editSupplier(@PathVariable(value = "supId") Long supId, Model model) {
		// get supplier from the service
		Supplier supplier = supplierService.getSupplierById(supId);

		// set supplier as a model attribute to pre-populate the form
		model.addAttribute("supplier", supplier);
		return "/supplier_edit";
	}

	// save updated supplier record
	@RequestMapping(value = "/supplier/update", method = RequestMethod.POST)
	public String updateSupplier(@ModelAttribute @Valid Supplier supplier, BindingResult result) {
		if (result.hasErrors()) {
			return "supplier_edit";
		}
		supplierService.updateSupplier(supplier);
		return "redirect:/supplier";
	}

	// deletes a specific supplier
	@RequestMapping(value = "/supplier/delete/{supId}", method = { RequestMethod.DELETE, RequestMethod.GET })
	private String deleteSupplier(@PathVariable(name = "supId") Long supId) {
		// call delete supplier method
		supplierService.deleteSupplier(supId);
		return "redirect:/supplier";
	}

	// pagination and sorting
	@GetMapping(path = "/page/{pageNo}")
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
