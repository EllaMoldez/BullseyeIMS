package ca.bullseye.ims.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.bullseye.ims.model.Supplier;
import ca.bullseye.ims.repositories.SupplierRepository;

@Service
public class SupplierService {

	@Autowired
	SupplierRepository supRepository;
	
	//CREATE
	public Supplier createSupplier(Supplier supplier) {
		return supRepository.save(supplier);
	}
	
	// READ
	public List<Supplier> getAllSuppliers() {
	    return supRepository.findAll();
	}

	// UPDATE
	public Supplier updateSupplier(Long supId, Supplier supplierDetails) {
			Supplier supplier = supRepository.findById(supId).get();
			supplier.setSupName(supplierDetails.getSupName());
			supplier.setSupAddress(supplierDetails.getSupAddress());
			supplier.setSupEmail(supplierDetails.getSupEmail());
	        
	        return supRepository.save(supplier);                                
	}
	
	// DELETE
		public void deleteSupplier(Long supId) {
		    supRepository.deleteById(supId);
		}
}
