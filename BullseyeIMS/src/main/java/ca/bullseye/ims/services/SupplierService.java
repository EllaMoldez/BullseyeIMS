package ca.bullseye.ims.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ca.bullseye.ims.model.Supplier;
import ca.bullseye.ims.repositories.SupplierRepository;

@Service
public class SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;
	
	/* Display all supplier records */
	public List<Supplier> getAllSuppliers() {
		return supplierRepository.findAll();
	}

	/* Sort supplier list */
	public List<Supplier> getAllSuppliers(Sort sort) {
		return supplierRepository.findAll();
	}

	/* Pagination */
	public Page<Supplier> getAllSuppliers(Pageable pageable) {
		return supplierRepository.findAll(pageable);
	}

	public Page<Supplier> getAllSuppliersOrderByOrdersSizeAsc(Pageable pageable) {
		return ((SupplierService) supplierRepository).getAllSuppliersOrderByOrdersSizeAsc(pageable);
	}

	public Page<Supplier> getAllSuppliersOrderByOrdersSizeDesc(Pageable pageable) {
		return ((SupplierService) supplierRepository).getAllSuppliersOrderByOrdersSizeDesc(pageable);
	}

	/* find supplier record by Id */
	public Optional<Supplier> getSupplierById(Long supId) {
		return supplierRepository.findById(supId);
	}

	public Page<Supplier> getSuppliersBySearchValue(String value, Pageable pageable) {
		Supplier supplier = new Supplier();
		supplier.setSupName(value);
		supplier.setSupContactPerson(value);
		supplier.setSupCountry(value);
		supplier.setSupEmail(value);
		supplier.setSupContact(value);

		ExampleMatcher.GenericPropertyMatcher propertyMatcher = ExampleMatcher.GenericPropertyMatchers.contains()
				.ignoreCase();
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withMatcher("supName", propertyMatcher)
				.withMatcher("supContactPerson", propertyMatcher).withMatcher("supCountry", propertyMatcher)
				.withMatcher("supEmail", propertyMatcher).withMatcher("supContact", propertyMatcher);
		Example<Supplier> supplierExample = Example.of(supplier, exampleMatcher);
		return supplierRepository.findAll(supplierExample, pageable);
	}

	public Supplier saveSupplier(Supplier supplier) {
		return supplierRepository.save(supplier);
	}

	public void deleteSupplier(Supplier supplier) {
		supplierRepository.delete(supplier);
	}

	public void deleteSupplierById(Long supId) {
		supplierRepository.deleteById(supId);
	}

	public boolean isSupplierExists(Long supId) {
		return supplierRepository.existsById(supId);
	}

	public long getTotalCount() {
		return supplierRepository.count();
	}

	public List<Map<String, Long>> getSupplierWithMaxOrders(Pageable pageable) {
		return ((SupplierService) supplierRepository).getSupplierWithMaxOrders(pageable);
	}

	/*
	 * Create new supplier record public Supplier addNewSupplier(Supplier supplier)
	 * { return supplierRepository.save(supplier); }
	 */

	/*
	 * // getting a specific supplier record public Supplier getSupplierById(Long
	 * supId) { Optional<Supplier> optional = supplierRepository.findById(supId);
	 * Supplier supplier = null; if (optional.isPresent()) { supplier =
	 * optional.get(); } else { throw new
	 * RuntimeException("Record not found for Supplier Id: " + supId); } return
	 * supplier; }
	 * 
	 * 
	 * // update supplier record public void updateSupplier(Supplier supplier) {
	 * supplierRepository.save(supplier); }
	 * 
	 * // delete a specific supplier public void deleteSupplier(Long supId) {
	 * this.supplierRepository.deleteById(supId); }
	 * 
	 * public boolean isSupplierExists(Long supId) { return
	 * this.supplierRepository.existsById(supId); }
	 * 
	 * // pagination and sorting public Page<Supplier> findPaginated(int pageNo, int
	 * pageSize, String sortField, String sortDirection) { Sort sort =
	 * sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
	 * Sort.by(sortField).ascending() : Sort.by(sortField).descending(); Pageable
	 * pageable = PageRequest.of(pageNo - 1, pageSize, sort); return
	 * this.supplierRepository.findAll(pageable); }
	 */

}
