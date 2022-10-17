
package ca.bullseye.ims.controllers;

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
			                      @RequestParam(defaultValue = "supName") String sort, 
			                      @RequestParam(defaultValue = "1") int page,
			                      @RequestParam(defaultValue = "15") int size, @RequestParam(defaultValue = "asc") String direction,
			                      Model model) {
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

	@GetMapping("/addSupplier")
	public String getAddSupplierPage(Model model) {
		model.addAttribute("pageTitle", "Create Supplier");
		model.addAttribute("supplier", new Supplier());
		return "supplier-edit";
	}

	@GetMapping("/updateSupplier")
	public String getUpdateSupplierPage(@RequestParam Long supId, Model model) {
		Supplier existingSupplier = this.supplierService.getSupplierById(supId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found."));
		model.addAttribute("pageTitle", "Edit Supplier");
		model.addAttribute("supplier", existingSupplier);
		return "supplier-edit";
	}
	
	@PostMapping("/saveSupplier")
	public String saveSupplier(@Valid @ModelAttribute Supplier supplier, Errors errors, Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("pageTitle", (supplier.getSupId() != null ? "Update" : "Create") + " Supplier");
			return "supplier-edit";
		} else {
			supplierService.saveSupplier(supplier);
			return "redirect:/supplier";
		}
	}
	
	@GetMapping("/deleteSupplier")
	public String deleteSupplier(@RequestParam Long supId, Model model) {
		if (!supplierService.isSupplierExists(supId)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found.");
		}

		supplierService.deleteSupplierById(supId);
		return "redirect:/supplier";
	}


	/*
	 * // display list of suppliers
	 * 
	 * @RequestMapping(value = "/supplier") public String viewSupplierList(Model
	 * model) { return findPaginated(1, "supId", "asc", model); }
	 * 
	 * // show new or create supplier page
	 * 
	 * @RequestMapping(value = "/supplier/create", method = RequestMethod.GET)
	 * public String newSupplier(Model model) { // create model attribute to bind
	 * form data Supplier supplier = new Supplier(); model.addAttribute(supplier);
	 * return "supplier_new"; }
	 * 
	 * // save new supplier to database
	 * 
	 * @RequestMapping(value = "/supplier/add", method = RequestMethod.POST) public
	 * String addNewSupplier(@ModelAttribute @Valid Supplier supplier, BindingResult
	 * result) { if (result.hasErrors()) { return "supplier_new"; }
	 * supplierService.addNewSupplier(supplier); return "redirect:/supplier"; }
	 * 
	 * // edit existing supplier record
	 * 
	 * @GetMapping(path = "/supplier/edit/{supId}") public String
	 * editSupplier(@PathVariable(value = "supId") Long supId, Model model) { // get
	 * supplier from the service Supplier supplier =
	 * supplierService.getSupplierById(supId);
	 * 
	 * // set supplier as a model attribute to pre-populate the form
	 * model.addAttribute("supplier", supplier); return "/supplier_edit"; }
	 * 
	 * // save updated supplier record
	 * 
	 * @RequestMapping(value = "/supplier/update", method = RequestMethod.POST)
	 * public String updateSupplier(@ModelAttribute @Valid Supplier supplier,
	 * BindingResult result) { if (result.hasErrors()) { return "supplier_edit"; }
	 * supplierService.updateSupplier(supplier); return "redirect:/supplier"; }
	 * 
	 * // deletes a specific supplier
	 * 
	 * @RequestMapping(value = "/supplier/delete/{supId}", method = {
	 * RequestMethod.DELETE, RequestMethod.GET }) private String
	 * deleteSupplier(@PathVariable(name = "supId") Long supId) {
	 * if(!this.supplierService.isSupplierExists(supId)) { throw new
	 * ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found."); }
	 * 
	 * // call delete supplier method supplierService.deleteSupplier(supId); return
	 * "redirect:/supplier"; }
	 * 
	 * // pagination and sorting
	 * 
	 * @GetMapping(path = "/page/{pageNo}") public String
	 * findPaginated(@PathVariable(value = "pageNo") int
	 * pageNo, @RequestParam("sortField") String sortField,
	 * 
	 * @RequestParam("sortDirection") String sortDirection, Model model) {
	 * 
	 * int pageSize = 6;
	 * 
	 * Page<Supplier> page = supplierService.findPaginated(pageNo, pageSize,
	 * sortField, sortDirection); List<Supplier> supplierList = page.getContent();
	 * 
	 * model.addAttribute("currentPage", pageNo); model.addAttribute("totalPages",
	 * page.getTotalPages()); model.addAttribute("totalItems",
	 * page.getTotalElements());
	 * 
	 * model.addAttribute("sortField", sortField);
	 * model.addAttribute("sortDirection", sortDirection);
	 * model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ?
	 * "desc" : "asc");
	 * 
	 * model.addAttribute("supplierList", supplierList); return "supplierlist"; }
	 */
}
