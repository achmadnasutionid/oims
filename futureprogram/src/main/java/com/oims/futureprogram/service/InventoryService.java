package com.oims.futureprogram.service;

import com.oims.futureprogram.model.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface InventoryService {

    Page<Inventory> getInventory(Pageable pageable);

    Inventory getOneInventory(Long inventoryId);

    Inventory createInventory(Inventory inventory);

    Inventory updateInventory(Long inventoryId, Inventory inventoryRequest);

    ResponseEntity<?> deleteInventory(Long inventoryId);
}
