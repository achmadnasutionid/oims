package com.oims.futureprogram.service.impl;

import com.oims.futureprogram.exception.ResourceNotFoundException;
import com.oims.futureprogram.model.ErrorCode;
import com.oims.futureprogram.model.Supervisor;
import com.oims.futureprogram.repository.SupervisorRepository;
import com.oims.futureprogram.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupervisorServiceImpl implements SupervisorService {

    @Autowired
    private SupervisorRepository supervisorRepository;

    @Override
    public List<Supervisor> getListSupervisor() {
        return supervisorRepository.findAll();
    }

    @Override
    public Optional<Supervisor> getSupervisorById(Long id) {
        if(!supervisorRepository.existsById(id)) {
            throw  new ResourceNotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        }
        return supervisorRepository.findById(id);
    }

    @Override
    public Supervisor createSupervisor(Supervisor supervisor) {
        return supervisorRepository.save(supervisor);
    }

    @Override
    public Supervisor updateSupervisor(Long supervisorId, Supervisor supervisorRequest) {
        return supervisorRepository.findById(supervisorId).map(supervisor -> {
            supervisor.setNama(supervisorRequest.getNama());
            supervisor.setHp(supervisorRequest.getHp());
            supervisor.setEmail(supervisorRequest.getEmail());
            return supervisorRepository.save(supervisor);
        }).orElseThrow(() -> new ResourceNotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
    }

    @Override
    public void deleteSupervisor(Long supervisorId) {
        if(!supervisorRepository.existsById(supervisorId)) {
            throw new ResourceNotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        }
        supervisorRepository.deleteById(supervisorId);
    }
}
