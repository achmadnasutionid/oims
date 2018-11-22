package com.oims.futureprogram.controller;

import com.oims.futureprogram.model.FormRequest;
import com.oims.futureprogram.service.FormRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class FormRequestController {

    @Autowired
    private FormRequestService formRequestService;

    @GetMapping("/formrequest")
    public Page<FormRequest> getFormRequest(Pageable pageable) {
        return formRequestService.getFormRequest(pageable);
    }

    @GetMapping("/formrequest/{formrequestId}")
    public FormRequest getOneFormRequest(@PathVariable Long formrequestId) {
        return formRequestService.getOneFormRequest(formrequestId);
    }

    @GetMapping("/employee/{employeeId}/formrequest")
    public List<FormRequest> getFormRequestByEmployeeId(@PathVariable Long employeeId) {
        return formRequestService.getFormRequestByEmployeeId(employeeId);
    }

    @PostMapping("/employee/{employeeId}/formrequest")
    public FormRequest createFormRequest(@Valid @RequestBody FormRequest formRequest, @PathVariable Long employeeId) {
        return formRequestService.createFormRequest(formRequest, employeeId);
    }

    @PutMapping("/employee/{employeeId}/formrequest/{formrequestId]")
    public FormRequest updateFormRequest(@PathVariable Long employeeId, @PathVariable Long formrequestId, @Valid @RequestBody FormRequest formRequestrequest) {
        return formRequestService.updateFormRequest(employeeId, formrequestId, formRequestrequest);
    }

    @DeleteMapping("/employee/{employeeId}/formrequest/{formrequestId}")
    public void deleteFormRequest(@PathVariable Long employeeId, @PathVariable Long formrequestId) {
    }
}
