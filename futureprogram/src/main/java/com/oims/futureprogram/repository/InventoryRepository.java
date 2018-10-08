package com.oims.futureprogram.repository;

import com.oims.futureprogram.model.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
