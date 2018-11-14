package com.oims.futureprogram.service;

import com.oims.futureprogram.model.FormRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FormRequestService {

    Page<FormRequest> getFormRequest(Pageable pageable);

    FormRequest getOneFormRequest(Long formrequestId);

    List<FormRequest> getFormRequestByEmployeeId(Long employeeId);

    FormRequest createFormRequest(FormRequest formRequest, Long employeeId);

    FormRequest updateFormRequest(Long employeeId, Long formrequestId, FormRequest formRequestrequest);

    //ResponseEntity<?> deleteTodo(Long employeeId, Long formrequestId);
}
