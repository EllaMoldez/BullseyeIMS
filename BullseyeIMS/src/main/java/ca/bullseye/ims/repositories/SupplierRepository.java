package ca.bullseye.ims.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.bullseye.ims.model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long>{
	

}
