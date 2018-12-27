package com.oims.futureprogram.service;

import com.oims.futureprogram.model.Inventory;

import java.util.List;
import java.util.Optional;

public interface InventoryService {

    List<Inventory> getListInventory();

    Optional<Inventory> getInventoryById(Long id);

    Inventory createInventory(Inventory inventory);

    Inventory updateInventory(Long inventoryId, Inventory inventoryRequest);

    void deleteInventory(Long inventoryId);
}
