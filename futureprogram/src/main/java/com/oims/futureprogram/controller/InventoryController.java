package com.oims.futureprogram.controller;

import com.oims.futureprogram.model.Inventory;
import com.oims.futureprogram.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/inventory")
    public Page<Inventory> getInventory(Pageable pageable) {
        return inventoryService.getInventory(pageable);
    }

    @GetMapping("/inventory/{inventoryId}")
    public Inventory getOneInventory(@PathVariable Long inventoryId) {
        return inventoryService.getOneInventory(inventoryId);
    }

    @PostMapping("/inventory")
    public Inventory createInventory(@Valid @RequestBody Inventory inventory) {
        return inventoryService.createInventory(inventory);
    }

    @PutMapping("inventory/{inventoryId}")
    public Inventory updateInventory(@PathVariable Long inventoryId, @Valid @RequestBody Inventory inventoryRequest) {
        return inventoryService.updateInventory(inventoryId, inventoryRequest);
    }

    @DeleteMapping("inventory/{inventoryId}")
    public ResponseEntity<?> deleteInventory(@PathVariable Long inventoryId) {
        return inventoryService.deleteInventory(inventoryId);
    }

}
