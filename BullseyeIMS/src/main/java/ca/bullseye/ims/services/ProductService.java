package ca.bullseye.ims.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.bullseye.ims.exceptions.RecordNotFoundException;
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

	// create new or update product details
	public void saveProduct(Product product) {
		productRepository.save(product);
	}

	// getting a specific product record
	public Product getProductById(Long id) throws RecordNotFoundException {
		if (productRepository.findById((Long) id).isPresent()) {
			return productRepository.findById((Long) id).get();
		} else if (productRepository.findById((Long) id).isEmpty()) {
			throw new RecordNotFoundException("" + id);
		}
		return null;
	}

	// deleting a specific record
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}



}
