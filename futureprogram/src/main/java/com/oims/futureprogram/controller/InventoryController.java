package com.oims.futureprogram.controller;

import com.oims.futureprogram.model.Inventory;
import com.oims.futureprogram.model.Response;
import com.oims.futureprogram.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class InventoryController extends GlobalController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/inventory")
    public Response<List<Inventory>> getListInventory() {
        return toResponse(inventoryService.getListInventory());
    }

    @GetMapping("/inventory/{id}")
    public Response<Inventory> getInventoryById(@PathVariable Long id) {
        return toResponse(inventoryService.getInventoryById(id));
    }

    @PostMapping("/inventory")
    public Response<Inventory> createInventory(@Valid @RequestBody Inventory inventory) {
        return toResponse(inventoryService.createInventory(inventory));
    }

    @PutMapping("/inventory/{inventoryId}")
    public Response<Inventory> updateInventory(@PathVariable Long inventoryId, @Valid @RequestBody Inventory inventoryRequest) {
        return toResponse(inventoryService.updateInventory(inventoryId, inventoryRequest));
    }

    @DeleteMapping("/inventory/{inventoryId}")
    @Transactional
    public void deleteInventory(@PathVariable Long inventoryId) {
        inventoryService.deleteInventory(inventoryId);
    }
}
