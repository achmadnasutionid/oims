package com.oims.futureprogram.controller;

import com.oims.futureprogram.exception.ResourceNotFoundException;
import com.oims.futureprogram.model.FormRequest;
import com.oims.futureprogram.model.Inventories;
import com.oims.futureprogram.repository.FormRequestRepository;
import com.oims.futureprogram.repository.InventoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class InventoriesController {

    @Autowired
    private InventoriesRepository inventoriesRepository;

    @Autowired
    private FormRequestRepository formRequestRepository;

    @GetMapping("/inventories")
    public Page<Inventories> getInventories(Pageable pageable) {
        return inventoriesRepository.findAll(pageable);
    }

    @GetMapping("/inventories/{inventoriesId}")
    public Inventories getOneInventories(@PathVariable Long inventoriesId) {
        return inventoriesRepository.findById(inventoriesId).orElseThrow(() -> new ResourceNotFoundException("Inventories not found with Id " + inventoriesId));
    }

    @GetMapping("/formrequest/{formrequestId}/inventories")
    public List<Inventories> getInventoriesByFormRequestId(@PathVariable Long formrequestId) {
        return inventoriesRepository.findByFormRequestId(formrequestId);
    }

    @PostMapping("/formrequest/{formrequestId}/inventories")
    public Inventories createInventories(@Valid @RequestBody Inventories inventories, @PathVariable Long formrequestId) {
        return formRequestRepository.findById(formrequestId).map(formRequest -> {
            inventories.setFormRequest(formRequest);
            return inventoriesRepository.save(inventories);
        }).orElseThrow(() -> new ResourceNotFoundException("Form Request not found with Id " + formrequestId));
    }

    @PutMapping("/formrequest/{formrequestId}/invetories/{inventoriesId}")
    public Inventories updateInventories(@PathVariable Long formrequestId, @PathVariable Long inventoriestId, @Valid @RequestBody Inventories inventoriesrequest) {
        if (!formRequestRepository.existsById(formrequestId)) {
            throw new ResourceNotFoundException("Form Request not found witn Id " + formrequestId);
        }
        return inventoriesRepository.findById(inventoriestId).map(inventories -> {
            inventories.setId_inventory(inventoriesrequest.getId_inventory());
            inventories.setJumlah_inventory(inventoriesrequest.getJumlah_inventory());
            return inventoriesRepository.save(inventories);
        }).orElseThrow(() -> new ResourceNotFoundException("Inventories not found with Id " + inventoriestId));
    }

    @DeleteMapping("/formrequest/{formrequestId}/inventories/{inventoriesId}")
    public ResponseEntity<?> deleteInventories(@PathVariable Long formrequestId, @PathVariable Long inventoriesId) {
        if (!formRequestRepository.existsById(formrequestId)) {
            throw new ResourceNotFoundException("Form Request not found with Id " + formrequestId);
        }
        return inventoriesRepository.findById(inventoriesId).map(inventories -> {
            inventoriesRepository.delete(inventories);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Inventories not found with Id " + inventoriesId));
    }
}
