package com.oims.futureprogram.service;

import com.oims.futureprogram.model.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InventoryService {

    Page<Inventory> getInventory(Pageable pageable);

    Inventory getOneInventory(Long inventoryId);

    Inventory createInventory(Inventory inventory);

    Inventory updateInventory(Long inventoryId, Inventory inventoryRequest);

    void deleteInventory(Long inventoryId);
}
