package ca.bullseye.ims.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.bullseye.ims.exceptions.ProductNotFoundException;
import ca.bullseye.ims.model.Product;
import ca.bullseye.ims.model.Supplier;
import ca.bullseye.ims.repositories.SupplierRepository;

@Service
public class SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;

	// display all supplier records
	public List<Supplier> getAllSuppliers() {
		return supplierRepository.findAll();
	}

	// create new or update supplier details
	public Supplier saveSupplier(Supplier supplier) {
		return supplierRepository.save(supplier);
	}

	// getting a specific supplier record
	public Supplier getSupplierById(Long id) throws ProductNotFoundException {
		if (supplierRepository.findById((Long) id).isPresent()) {
			return supplierRepository.findById((Long) id).get();
		} else if (supplierRepository.findById((Long) id).isEmpty()) {
			throw new ProductNotFoundException("" + id);
		}
		return null;
	}

	/*
	 * // UPDATE public Supplier updateSupplier(Long supId, Supplier
	 * supplierDetails) { Supplier supplier =
	 * supplierRepository.findById(supId).get();
	 * supplier.setSupName(supplierDetails.getSupName());
	 * supplier.setSupAddress(supplierDetails.getSupAddress());
	 * supplier.setSupEmail(supplierDetails.getSupEmail());
	 * 
	 * return supplierRepository.save(supplier); }
	 */

	// delete a specific supplier
	public void deleteSupplier(Long supId) {
		supplierRepository.deleteById(supId);
	}
}
