package ca.bullseye.ims.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.bullseye.ims.model.Product;
import ca.bullseye.ims.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	// display all product records
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	// create new product record
	public void addNewProduct(Product product) {
		productRepository.save(product);
	}
	
	// getting a specific product record
	public Product getProductById(Long prodId) {
		Optional<Product> optional = productRepository.findById(prodId);
		Product product = null;
		
		if(optional.isPresent()) {
			product = optional.get();
		}else {
			throw new RuntimeException("Record not found for Product Id: " + prodId);
		}
		return product;
	}

	/*
	 * // getting a specific product record public Product getProductById(Long id)
	 * throws RecordNotFoundException { if (productRepository.findById((Long)
	 * id).isPresent()) { return productRepository.findById((Long) id).get(); } else
	 * if (productRepository.findById((Long) id).isEmpty()) { throw new
	 * RecordNotFoundException("" + id); } return null; }
	 */

	// update product record
	public void updateProduct(Product product) {
		productRepository.save(product);
	}

	// deleting a specific record
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

}
