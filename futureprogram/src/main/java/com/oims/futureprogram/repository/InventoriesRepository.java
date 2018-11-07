package com.oims.futureprogram.repository;

import com.oims.futureprogram.model.Inventories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoriesRepository extends JpaRepository<Inventories, Long> {
    List<Inventories> findByFormRequestId(Long formrequestId);
}
