package com.oims.futureprogram.service;

import com.oims.futureprogram.model.Supervisor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupervisorService {

    Page<Supervisor> getSupervisor(Pageable pageable);

    Supervisor getOneSupervisor(Long supervisorId);

    Supervisor createSupervisor(Supervisor supervisor);

    Supervisor updateSupervisor(Long supervisorId, Supervisor supervisorRequest);

    void deleteSupervisor(Long supervisorId);
}
