package com.oims.futureprogram.repository;

import com.oims.futureprogram.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    void deleteInventoryById(Long id);

}
