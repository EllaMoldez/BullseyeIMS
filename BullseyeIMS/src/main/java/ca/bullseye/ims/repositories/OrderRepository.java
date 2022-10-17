package ca.bullseye.ims.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.bullseye.ims.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
<<<<<<< Updated upstream
//	List<Order> findByProductId(Long productId);
=======
	//List<Order> findByProductId(Long productId);
>>>>>>> Stashed changes
}
