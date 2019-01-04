package com.oims.futureprogram.controller;

import com.oims.futureprogram.model.FormRequest;
import com.oims.futureprogram.model.Response;
import com.oims.futureprogram.service.FormRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FormRequestController extends GlobalController {

    @Autowired
    private FormRequestService formRequestService;

    @GetMapping("/formrequest")
    public Response<List<FormRequest>> getListFormRequest() {
        return toResponse(formRequestService.getListFormRequest());
    }

    @GetMapping("/formrequest/{id}")
    public Response<FormRequest> getFormRequestById(@PathVariable Long id) {
        return toResponse(formRequestService.getFormRequestById(id));
    }

    @PostMapping("/formrequest")
    public Response<FormRequest> createFormRequest(@Valid @RequestBody FormRequest formRequest) {
        return toResponse(formRequestService.createFormRequest(formRequest));
    }

    @PutMapping("/formrequest/{formRequestId}")
    public Response<FormRequest> updateFormRequest(@PathVariable Long formRequestId, @Valid @RequestBody FormRequest formRequestRequest) {
        return toResponse(formRequestService.updateFormRequest(formRequestId, formRequestRequest));
    }

    @PutMapping("/formrequest/approve/{id}")
    public Response<FormRequest> approveFormRequest(@PathVariable Long id) {
        return toResponse(formRequestService.approveFormRequest(id));
    }

    @PutMapping("/formrequest/reject/{id}")
    public Response<FormRequest> rejectFormRequest(@PathVariable Long id) {
        return toResponse(formRequestService.rejectFormRequest(id));
    }

    @PutMapping("/formrequest/cancel/{id}")
    public Response<FormRequest> cancelFormRequest(@PathVariable Long id) {
        return toResponse(formRequestService.cancelFormRequest(id));
    }

    @PutMapping("/formrequest/handover/{id}")
    public Response<FormRequest> handoverFormRequest(@PathVariable Long id) {
        return toResponse(formRequestService.handoverFormRequest(id));
    }

    @DeleteMapping("/formrequest/{formRequestId}")
    @Transactional
    public void deleteFormRequest(@PathVariable Long formRequestId) {
        formRequestService.deleteFormRequest(formRequestId);
    }

    @GetMapping("formrequest/{supervisorId}/{pending}/{approved}/{rejected}")
    public Response<List<FormRequest>> getListFormRequestSupervisor(@PathVariable Long supervisorId, @PathVariable String pending, @PathVariable String approved, @PathVariable String rejected) {
        return toResponse(formRequestService.getListFormRequestSupervisor(supervisorId, pending, approved, rejected));
    }

    @GetMapping("formrequest/{approved}")
    public Response<List<FormRequest>> getListFormRequestAdmin(@PathVariable String approved) {
        return toResponse(formRequestService.getListFormRequestAdmin(approved));
    }
}