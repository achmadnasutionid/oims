package com.oims.futureprogram.controller;

import com.oims.futureprogram.exception.ResourceNotFoundException;
import com.oims.futureprogram.model.FormRequest;
import com.oims.futureprogram.repository.EmployeeRepository;
import com.oims.futureprogram.repository.FormRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class FormRequestController {

    @Autowired
    private FormRequestRepository formrequestRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/formrequest")
    public Page<FormRequest> getFormRequest(Pageable pageable) {
        return formrequestRepository.findAll(pageable);
    }

    @GetMapping("/formrequest/{formrequestId}")
    public FormRequest getOneFormRequest(@PathVariable Long formrequestId) {
        return formrequestRepository.findById(formrequestId).orElseThrow(() -> new ResourceNotFoundException("Form Request not found with Id " + formrequestId));
    }

    @GetMapping("/employee/{employeeId}/formrequest")
    public List<FormRequest> getFormRequestByEmployeeId(@PathVariable Long employeeId) {
        return formrequestRepository.findByEmployeeId(employeeId);
    }

    @PostMapping("/employee/{employeeId}/formrequest")
    public FormRequest createFormRequest(@Valid @RequestBody FormRequest formrequest, @PathVariable Long employeeId) {
        return employeeRepository.findById(employeeId).map(employee -> {
            formrequest.setEmployee(employee);
            return formrequestRepository.save(formrequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Employee not found with Id "+employeeId));
    }

    @PutMapping("/employee/{employeeId}/formrequest/{formrequestId]")
    public FormRequest updateFormRequest(@PathVariable Long employeeId, @PathVariable Long formrequestId, @Valid @RequestBody FormRequest formRequestrequest) {
        if(!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee not found with Id " + employeeId);
        }
        return formrequestRepository.findById(formrequestId).map(formRequest -> {
            formRequest.setInventory(formRequestrequest.getInventory());
            formRequest.setTanggal(formRequestrequest.getTanggal());
            formRequest.setStatus(formRequestrequest.getStatus());
            return formrequestRepository.save(formRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Form Request not found with Id " + formrequestId));
    }

    @DeleteMapping("/employee/{employeeId}/formrequest/{formrequestId}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long employeeId, @PathVariable Long formrequestId) {
        if(!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee not found with Id "+ employeeId);
        }
        return formrequestRepository.findById(formrequestId).map(formRequest -> {
            formrequestRepository.delete(formRequest);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Form Request not found with Id" + formrequestId));
    }
}
