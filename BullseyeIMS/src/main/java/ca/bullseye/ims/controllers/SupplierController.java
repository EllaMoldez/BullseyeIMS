package ca.bullseye.ims.controllers;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import ca.bullseye.ims.model.Supplier;
import ca.bullseye.ims.services.SupplierService;

@RestController
@RequestMapping("/") 
public class SupplierController {
	@Autowired
	SupplierService supService;
	
	@RequestMapping(value="/suppliers", method=RequestMethod.POST)
	public Supplier createSupplier(@RequestBody Supplier supplier) {
	    return supService.createSupplier(supplier);
	}
	
	@RequestMapping(value="/suppliers", method=RequestMethod.GET)
	public List<Supplier> readSuppliers() {
	    return supService.getAllSuppliers();
	}

	@RequestMapping(value="/suppliers/{supId}", method=RequestMethod.PUT)
	public Supplier readSuppliers(@PathVariable(value = "empId") Long supId, @RequestBody Supplier supplierDetails) {
	    return supService.updateSupplier(supId, supplierDetails);
	}

	@RequestMapping(value="/suppliers/{supId}", method=RequestMethod.DELETE)
	public void deleteEmployees(@PathVariable(value = "supId") Long supId) {
		supService.deleteSupplier(supId);
	}
}
