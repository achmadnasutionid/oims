package com.oims.futureprogram.service.impl;

import com.oims.futureprogram.exception.ResourceNotFoundException;
import com.oims.futureprogram.model.ErrorCode;
import com.oims.futureprogram.model.FormRequest;
import com.oims.futureprogram.model.Inventory;
import com.oims.futureprogram.repository.FormRequestRepository;
import com.oims.futureprogram.repository.InventoryRepository;
import com.oims.futureprogram.service.FormRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormRequestServiceImpl implements FormRequestService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private FormRequestRepository formRequestRepository;

    @Override
    public List<FormRequest> getListFormRequest() {
        return formRequestRepository.findAll();
    }

    @Override
    public Optional<FormRequest> getFormRequestById(Long id) {
        if(!formRequestRepository.existsById(id)) {
            throw new ResourceNotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        }
        return formRequestRepository.findById(id);
    }

    @Override
    public List<FormRequest> getListFormRequestSupervisor(Long supervisorId, String pending, String approved, String rejected) {
        return formRequestRepository.findAllBySupervisorIdAndStatusOrStatusOrStatus(supervisorId, pending, approved, rejected);
    }

    @Override
    public List<FormRequest> getListFormRequestAdmin(String approved) {
        return formRequestRepository.findAllByStatus(approved);
    }

    @Override
    public FormRequest createFormRequest(FormRequest formRequest) {
        Inventory inventory = inventoryRepository.findById(formRequest.getInventoryId()).orElseThrow(() -> new ResourceNotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
        Long currentJumlah = inventory.getJumlah();
        inventory.setJumlah(currentJumlah - formRequest.getJumlah());
        inventoryRepository.save(inventory);
        return formRequestRepository.save(formRequest);
    }

    @Override
    public FormRequest updateFormRequest(Long formRequestId, FormRequest formRequestRequest) {
        return formRequestRepository.findById(formRequestId).map(formRequest -> {
            Long inventoryId = formRequestRequest.getInventoryId();
            Long oldInventoryId = formRequest.getInventoryId();
            Long jumlah = formRequestRequest.getJumlah();
            Long oldJumlah = formRequest.getJumlah();

            if(oldInventoryId != inventoryId) {
                Inventory oldInventory = inventoryRepository.findById(oldInventoryId).orElseThrow(() -> new ResourceNotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
                Long currentJumlah = oldInventory.getJumlah();
                oldInventory.setJumlah(currentJumlah + oldJumlah);
                inventoryRepository.save(oldInventory);
            }

            Inventory inventory = inventoryRepository.findById(inventoryId).orElseThrow(() -> new ResourceNotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
            Long currentJumlah = inventory.getJumlah();
            inventory.setJumlah(currentJumlah - (jumlah - oldJumlah));
            inventoryRepository.save(inventory);

            formRequest.setStatus(formRequestRequest.getStatus());
            formRequest.setJumlah(formRequestRequest.getJumlah());
            return formRequestRepository.save(formRequest);
        }).orElseThrow(() -> new ResourceNotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
    }

    @Override
    public FormRequest approveFormRequest(Long id) {
        return formRequestRepository.findById(id).map(formRequest -> {
            formRequest.setStatus("APPROVED");
            return formRequestRepository.save(formRequest);
        }).orElseThrow(() -> new ResourceNotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
    }

    @Override
    public FormRequest rejectFormRequest(Long id) {
        return formRequestRepository.findById(id).map(formRequest -> {
            Long inventoryId = formRequest.getInventoryId();
            Inventory inventory = inventoryRepository.findById(inventoryId).orElseThrow(() -> new ResourceNotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
            Long currentJumlah = inventory.getJumlah();
            inventory.setJumlah(currentJumlah + formRequest.getJumlah());
            inventoryRepository.save(inventory);
            formRequest.setStatus("REJECTED");
            return formRequestRepository.save(formRequest);
        }).orElseThrow(() -> new ResourceNotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
    }

    @Override
    public FormRequest cancelFormRequest(Long id) {
        return formRequestRepository.findById(id).map(formRequest -> {
            Long inventoryId = formRequest.getInventoryId();
            Inventory inventory = inventoryRepository.findById(inventoryId).orElseThrow(() -> new ResourceNotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
            Long currentJumlah = inventory.getJumlah();
            inventory.setJumlah(currentJumlah + formRequest.getJumlah());
            inventoryRepository.save(inventory);
            formRequest.setStatus("CANCELLED");
            return formRequestRepository.save(formRequest);
        }).orElseThrow(() -> new ResourceNotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
    }

    @Override
    public FormRequest handoverFormRequest(Long id) {
        return formRequestRepository.findById(id).map(formRequest -> {
            formRequest.setStatus("APPROVED");
            return formRequestRepository.save(formRequest);
        }).orElseThrow(() -> new ResourceNotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
    }

    @Override
    public void deleteFormRequest(Long formRequestId) {
        FormRequest formRequest = formRequestRepository.findById(formRequestId).orElseThrow(() -> new ResourceNotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
        if (formRequest.getStatus().equalsIgnoreCase("PENDING")) {
            Inventory inventory = inventoryRepository.findById(formRequest.getId()).orElseThrow(() -> new ResourceNotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
            Long currentJumlah = inventory.getJumlah();
            inventory.setJumlah(currentJumlah + formRequest.getJumlah());
        }
        formRequestRepository.deleteFormRequestById(formRequestId);
    }
}
