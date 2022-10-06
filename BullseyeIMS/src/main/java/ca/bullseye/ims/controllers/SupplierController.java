
package ca.bullseye.ims.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ca.bullseye.ims.exceptions.ProductNotFoundException;
import ca.bullseye.ims.model.Supplier;
import ca.bullseye.ims.services.SupplierService;

@Controller
public class SupplierController {

	@Autowired
	SupplierService supplierService;
	
	
	//list of all suppliers
	@RequestMapping(path = "/supplier")
	public String viewSupplier(Model model) {
		List<Supplier> supplierList = supplierService.getAllSuppliers();
		model.addAttribute("supplierList", supplierList);

		return "supplierlist";
	}
	
	//create new supplier	
	@RequestMapping("/supplier/add")
	public String newProduct(Model model) {
		Supplier supplier = new Supplier();
		model.addAttribute(supplier);

		return "supplier_new";
	}
	
	//save changes on supplier details
	@RequestMapping(value = "/supplier/save", method = RequestMethod.POST)
	public String saveSupplier(@ModelAttribute("supplier") Supplier supplier) {
		supplierService.saveSupplier(supplier);
		return "redirect:/supplier";
	}
	
	//edit a supplier details
	@RequestMapping("/supplier/edit/{supId}")
	public ModelAndView EditSupplier(@PathVariable(name = "supId") Long supId) throws ProductNotFoundException {
		ModelAndView mav = new ModelAndView("supplier_edit");
		Supplier supplier = supplierService.getSupplierById(supId);
		mav.addObject("supplier", supplier);
		return mav;
	}

	// deletes a specific supplier
	@RequestMapping("supplier/delete/{supId}")
	private String deleteSupplier(@PathVariable(name = "supId") Long supId) {
		supplierService.deleteSupplier(supId);
		return "redirect:/supplier";
	}
}
