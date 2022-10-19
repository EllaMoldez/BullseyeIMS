package ca.bullseye.ims.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.bullseye.ims.model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{

}
