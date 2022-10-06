package ca.bullseye.ims.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.bullseye.ims.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
}
