package com.oims.futureprogram.controller;

import com.oims.futureprogram.exception.ResourceNotFoundException;
import com.oims.futureprogram.model.Inventory;
import com.oims.futureprogram.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @GetMapping("/inventory")
    public Page<Inventory> getInventory(Pageable pageable) {
        return inventoryRepository.findAll(pageable);
    }

    @PostMapping("/inventory")
    public Inventory createInventory(@Valid @RequestBody Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @PutMapping("inventory/{inventoryId}")
    public Inventory updateInventory(@PathVariable Long inventoryId, @Valid @RequestBody Inventory inventoryRequest) {
        return inventoryRepository.findById(inventoryId).map(inventory -> {
            inventory.setNama(inventoryRequest.getNama());
            inventory.setHarga(inventoryRequest.getHarga());
            inventory.setJumlah(inventoryRequest.getJumlah());
            inventory.setDeskripsi(inventoryRequest.getDeskripsi());
            return inventoryRepository.save(inventory);
        }).orElseThrow(() -> new ResourceNotFoundException("Inventory not found with Id " + inventoryId));
    }

    @DeleteMapping("inventory/{inventoryId}")
    public ResponseEntity<?> deleteInventory(@PathVariable Long inventoryId) {
        return inventoryRepository.findById(inventoryId).map(inventory -> {
            inventoryRepository.delete(inventory);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Inventory not found with Id " + inventoryId));
    }
}
