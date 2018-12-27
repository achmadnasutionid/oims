package com.oims.futureprogram.service.impl;

import com.oims.futureprogram.exception.ResourceNotFoundException;
import com.oims.futureprogram.model.ErrorCode;
import com.oims.futureprogram.model.Inventory;
import com.oims.futureprogram.repository.InventoryRepository;
import com.oims.futureprogram.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public List<Inventory> getListInventory() {
        return inventoryRepository.findAll();
    }

    @Override
    public Optional<Inventory> getInventoryById(Long id) {
        if(!inventoryRepository.existsById(id)) {
            throw new ResourceNotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        }
        return inventoryRepository.findById(id);
    }

    @Override
    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory updateInventory(Long inventoryId, Inventory inventoryRequest) {
        return inventoryRepository.findById(inventoryId).map(inventory -> {
            inventory.setNama(inventoryRequest.getNama());
            inventory.setHarga(inventoryRequest.getHarga());
            inventory.setJumlah(inventory.getJumlah() + inventoryRequest.getJumlah());
            inventory.setDeskripsi(inventoryRequest.getDeskripsi());
            return inventoryRepository.save(inventory);
        }).orElseThrow(() -> new ResourceNotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
    }

    @Override
    public void deleteInventory(Long inventoryId) {
        if(!inventoryRepository.existsById(inventoryId)) {
            throw new ResourceNotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        }
        inventoryRepository.deleteInventoryById(inventoryId);
    }
}
