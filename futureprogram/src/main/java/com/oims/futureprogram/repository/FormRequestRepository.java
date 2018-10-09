package com.oims.futureprogram.repository;

import com.oims.futureprogram.model.FormRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormRequestRepository extends JpaRepository<FormRequest, Long> {
    List<FormRequest> findByEmployeeId(Long employeeId);
}
