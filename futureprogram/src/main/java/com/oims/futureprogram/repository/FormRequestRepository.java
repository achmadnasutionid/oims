package com.oims.futureprogram.repository;

import com.oims.futureprogram.model.FormRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormRequestRepository extends JpaRepository<FormRequest, Long> {

    void deleteFormRequestById(Long id);

    List<FormRequest> findAllBySupervisorIdAndStatusOrStatusOrStatus(Long id, String pending, String approved, String rejected);

    List<FormRequest> findAllByStatus(String approved);
    
}
