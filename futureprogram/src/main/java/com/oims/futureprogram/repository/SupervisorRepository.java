package com.oims.futureprogram.repository;

import com.oims.futureprogram.model.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {

    void deleteSupervisorById(Long id);

}
