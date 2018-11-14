package com.oims.futureprogram.service.impl;

import com.oims.futureprogram.exception.ResourceNotFoundException;
import com.oims.futureprogram.model.Inventories;
import com.oims.futureprogram.repository.FormRequestRepository;
import com.oims.futureprogram.repository.InventoriesRepository;
import com.oims.futureprogram.service.InventoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoriesServiceImpl implements InventoriesService {
    @Autowired
    private InventoriesRepository inventoriesRepository;

    @Autowired
    private FormRequestRepository formRequestRepository;

    public Page<Inventories> getInventories(Pageable pageable) {
        return inventoriesRepository.findAll(pageable);
    }

    public Inventories getOneInventories(Long inventoriesId) {
        return inventoriesRepository.findById(inventoriesId).orElseThrow(() -> new ResourceNotFoundException("Inventories not found with Id " + inventoriesId));
    }

    public List<Inventories> getInventoriesByFormRequestId(Long formrequestId) {
        return inventoriesRepository.findByFormRequestId(formrequestId);
    }

    public Inventories createInventories(Inventories inventories, Long formrequestId) {
        return formRequestRepository.findById(formrequestId).map(formRequest -> {
            inventories.setFormRequest(formRequest);
            return inventoriesRepository.save(inventories);
        }).orElseThrow(() -> new ResourceNotFoundException("Form Request not found with Id " + formrequestId));
    }

    public Inventories updateInventories(Long formrequestId, Long inventoriestId, Inventories inventoriesrequest) {
        if (!formRequestRepository.existsById(formrequestId)) {
            throw new ResourceNotFoundException("Form Request not found witn Id " + formrequestId);
        }
        return inventoriesRepository.findById(inventoriestId).map(inventories -> {
            inventories.setId_inventory(inventoriesrequest.getId_inventory());
            inventories.setJumlah_inventory(inventoriesrequest.getJumlah_inventory());
            return inventoriesRepository.save(inventories);
        }).orElseThrow(() -> new ResourceNotFoundException("Inventories not found with Id " + inventoriestId));
    }

    /*public ResponseEntity<?> deleteInventories(Long formrequestId, Long inventoriesId) {
        if (!formRequestRepository.existsById(formrequestId)) {
            throw new ResourceNotFoundException("Form Request not found with Id " + formrequestId);
        }
        return inventoriesRepository.findById(inventoriesId).map(inventories -> {
            inventoriesRepository.delete(inventories);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Inventories not found with Id " + inventoriesId));
    }*/
}
