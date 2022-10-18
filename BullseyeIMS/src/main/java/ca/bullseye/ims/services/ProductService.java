package ca.bullseye.ims.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import ca.bullseye.ims.model.Product;
import ca.bullseye.ims.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	/* Display all product records */
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	/* Sort supplier list */
	public List<Product> getAllProducts(Sort sort) {
		return productRepository.findAll(sort);
	}

	/* Pagination */
	public Page<Product> getAllProducts(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	public List<Product> getProductsByIds(Iterable<Long> prodIds) {
		return productRepository.findAllById(prodIds);
	}

	public Optional<Product> getProductById(Long prodId) {
		return productRepository.findById(prodId);
	}

	public Page<Product> getProductsBySearchValue(String value, Pageable pageable) {
		Product product = new Product();
		product.setProdSKU(value);
		product.setProdName(value);
		product.setProdBrand(value);

		ExampleMatcher.GenericPropertyMatcher propertyMatcher = ExampleMatcher.GenericPropertyMatchers.contains()
				.ignoreCase();
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withMatcher("prodSKU", propertyMatcher)
				.withMatcher("prodName", propertyMatcher).withMatcher("prodBrand", propertyMatcher);
		Example<Product> productExample = Example.of(product, exampleMatcher);
		return productRepository.findAll(productExample, pageable);
	}

	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	public void deleteProduct(Product product) {
		productRepository.delete(product);
	}

	public void deleteProductById(Long prodId) {
		productRepository.deleteById(prodId);
	}

	public boolean isProductExists(Long prodId) {
		return productRepository.existsById(prodId);
	}

	public long getTotalCount() {
		return productRepository.count();
	}

	/*
	 * // create new product record public void addNewProduct(Product product) {
	 * productRepository.save(product); }
	 * 
	 * 
	 * // getting a specific product record public Product getProductById(Long
	 * prodId) { Optional<Product> optional = productRepository.findById(prodId);
	 * Product product = null;
	 * 
	 * if(optional.isPresent()) { product = optional.get(); }else { throw new
	 * RuntimeException("Record not found for Product Id: " + prodId); } return
	 * product; }
	 * 
	 * 
	 * 
	 * // getting a specific product record public Product getProductById(Long id)
	 * throws RecordNotFoundException { if (productRepository.findById((Long)
	 * id).isPresent()) { return productRepository.findById((Long) id).get(); } else
	 * if (productRepository.findById((Long) id).isEmpty()) { throw new
	 * RecordNotFoundException("" + id); } return null; }
	 * 
	 * 
	 * // update product record public void updateProduct(Product product) {
	 * productRepository.save(product); }
	 * 
	 * // deleting a specific record public void deleteProduct(Long id) {
	 * productRepository.deleteById(id); }
	 */
}
