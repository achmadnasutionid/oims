package com.oims.futureprogram.service.impl;

import com.oims.futureprogram.exception.ResourceNotFoundException;
import com.oims.futureprogram.model.Inventory;
import com.oims.futureprogram.repository.InventoryRepository;
import com.oims.futureprogram.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public Page<Inventory> getInventory(Pageable pageable) {
        return inventoryRepository.findAll(pageable);
    }

    public Inventory getOneInventory(Long inventoryId) {
        return inventoryRepository.findById(inventoryId).orElseThrow(() -> new ResourceNotFoundException("Inventory not found with Id " + inventoryId));
    }

    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Inventory updateInventory(Long inventoryId, Inventory inventoryRequest) {
        return inventoryRepository.findById(inventoryId).map(inventory -> {
            inventory.setNama(inventoryRequest.getNama());
            inventory.setHarga(inventoryRequest.getHarga());
            inventory.setJumlah(inventory.getJumlah() + inventoryRequest.getJumlah());
            inventory.setDeskripsi(inventoryRequest.getDeskripsi());
            return inventoryRepository.save(inventory);
        }).orElseThrow(() -> new ResourceNotFoundException("Inventory not found with Id " + inventoryId));
    }

    public void deleteInventory(Long inventoryId) {
        if (!inventoryRepository.existsById(inventoryId)) {
            throw new ResourceNotFoundException("Inventory not found with Id " + inventoryId);
        }
        Inventory inventory = getOneInventory(inventoryId);
        inventoryRepository.delete(inventory);
    }
}
