package com.oims.futureprogram.service.impl;

import com.oims.futureprogram.exception.ResourceNotFoundException;
import com.oims.futureprogram.model.Supervisor;
import com.oims.futureprogram.repository.SupervisorRepository;
import com.oims.futureprogram.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SupervisorServiceImpl implements SupervisorService {

    @Autowired
    private SupervisorRepository supervisorRepository;

    public Page<Supervisor> getSupervisor(Pageable pageable) {
        return supervisorRepository.findAll(pageable);
    }

    public Supervisor getOneSupervisor(Long supervisorId) {
        return supervisorRepository.findById(supervisorId).orElseThrow(() -> new ResourceNotFoundException("Supervisor not found with Id " + supervisorId));
    }

    public Supervisor createSupervisor(Supervisor supervisor) {
        return supervisorRepository.save(supervisor);
    }

    public Supervisor updateSupervisor(Long supervisorId, Supervisor supervisorRequest) {
        return supervisorRepository.findById(supervisorId).map(supervisor -> {
            supervisor.setNama(supervisorRequest.getNama());
            supervisor.setHp(supervisorRequest.getHp());
            supervisor.setEmail(supervisorRequest.getEmail());
            return supervisorRepository.save(supervisor);
        }).orElseThrow(() -> new ResourceNotFoundException("Supervisor not found with Id " + supervisorId));
    }

    public void deleteSupervisor(Long supervisorId) {
        if (!supervisorRepository.existsById(supervisorId)) {
            throw new ResourceNotFoundException("Supervisor not found with Id " + supervisorId);
        }
        Supervisor supervisor = getOneSupervisor(supervisorId);
        supervisorRepository.delete(supervisor);
    }
}
