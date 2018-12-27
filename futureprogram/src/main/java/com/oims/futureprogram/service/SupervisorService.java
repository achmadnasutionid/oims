package com.oims.futureprogram.service;

import com.oims.futureprogram.model.Supervisor;

import java.util.List;
import java.util.Optional;

public interface SupervisorService {

    List<Supervisor> getListSupervisor();

    Optional<Supervisor> getSupervisorById(Long id);

    Supervisor createSupervisor(Supervisor supervisor);

    Supervisor updateSupervisor(Long supervisorId, Supervisor supervisorRequest);

    void deleteSupervisor(Long supervisorId);

}
