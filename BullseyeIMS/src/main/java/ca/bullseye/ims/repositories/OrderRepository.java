
package ca.bullseye.ims.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.bullseye.ims.model.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

}
