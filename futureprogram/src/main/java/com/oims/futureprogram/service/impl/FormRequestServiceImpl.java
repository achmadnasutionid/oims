package com.oims.futureprogram.service.impl;

import com.oims.futureprogram.exception.ResourceNotFoundException;
import com.oims.futureprogram.model.FormRequest;
import com.oims.futureprogram.repository.EmployeeRepository;
import com.oims.futureprogram.repository.FormRequestRepository;
import com.oims.futureprogram.service.FormRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormRequestServiceImpl implements FormRequestService {

    @Autowired
    private FormRequestRepository formRequestRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Page<FormRequest> getFormRequest(Pageable pageable) {
        return formRequestRepository.findAll(pageable);
    }

    public FormRequest getOneFormRequest(Long formrequestId) {
        return formRequestRepository.findById(formrequestId).orElseThrow(() -> new ResourceNotFoundException("Form Request not found with Id " + formrequestId));
    }

    public List<FormRequest> getFormRequestByEmployeeId(Long employeeId) {
        return formRequestRepository.findByEmployeeId(employeeId);
    }

    public FormRequest createFormRequest(FormRequest formRequest, Long employeeId) {
        return employeeRepository.findById(employeeId).map(employee -> {
            formRequest.setEmployee(employee);
            return formRequestRepository.save(formRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Employee not found with Id "+employeeId));
    }

    public FormRequest updateFormRequest(Long employeeId, Long formrequestId, FormRequest formRequestrequest) {
        if(!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee not found with Id " + employeeId);
        }
        return formRequestRepository.findById(formrequestId).map(formRequest -> {
            formRequest.setTanggal(formRequestrequest.getTanggal());
            formRequest.setStatus(formRequestrequest.getStatus());
            return formRequestRepository.save(formRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Form Request not found with Id " + formrequestId));
    }

    public void deleteFormRequest(Long employeeId, Long formrequestId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee not found with Id " + employeeId);
        }
        if (!formRequestRepository.existsById(formrequestId)) {
            throw new ResourceNotFoundException("Form Request not found with Id " + formrequestId);
        }
        else {
            FormRequest formRequest = getOneFormRequest(formrequestId);
            formRequestRepository.delete(formRequest);
        }
    }
}
