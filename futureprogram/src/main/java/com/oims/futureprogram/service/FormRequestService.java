package com.oims.futureprogram.service;

import com.oims.futureprogram.model.FormRequest;

import java.util.List;
import java.util.Optional;

public interface FormRequestService {

    List<FormRequest> getListFormRequest();

    Optional<FormRequest> getFormRequestById(Long id);

    List<FormRequest> getListFormRequestSupervisor(Long supervisorId, String pending, String approved, String rejected);

    List<FormRequest> getListFormRequestAdmin(String approved);

    FormRequest createFormRequest(FormRequest formRequest);

    FormRequest updateFormRequest(Long formRequestId, FormRequest formRequestRequest);

    FormRequest approveFormRequest(Long id);

    FormRequest cancelFormRequest(Long id);

    FormRequest rejectFormRequest(Long id);

    FormRequest handoverFormRequest(Long id);

    void deleteFormRequest(Long formRequestId);
}
