package com.oims.futureprogram.controller;

import com.oims.futureprogram.exception.ResourceNotFoundException;
import com.oims.futureprogram.model.Supervisor;
import com.oims.futureprogram.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class SupervisorController {

    @Autowired
    private SupervisorRepository supervisorRepository;

    @GetMapping("/supervisor")
    public Page<Supervisor> getSupervisor(Pageable pageable) {
        return supervisorRepository.findAll(pageable);
    }

    @PostMapping("supervisor")
    public Supervisor createSupervisor(@Valid @RequestBody Supervisor supervisor) {
        return supervisorRepository.save(supervisor);
    }

    @PutMapping("/supervisor/{supervisorId}")
    public Supervisor updateSupervisor(@PathVariable Long supervisorId, @Valid @RequestBody Supervisor supervisorRequest) {
        return supervisorRepository.findById(supervisorId).map(supervisor -> {
            supervisor.setNama(supervisorRequest.getNama());
            supervisor.setHp(supervisorRequest.getHp());
            supervisor.setEmail(supervisorRequest.getEmail());
            return supervisorRepository.save(supervisor);
        }).orElseThrow(() -> new ResourceNotFoundException("Supervisor not found with Id " + supervisorId));
    }

    @DeleteMapping("/supervisor/{supervisorId}")
    public ResponseEntity<?> deleteSupervisor(@PathVariable Long supervisorId) {
        return supervisorRepository.findById(supervisorId).map(supervisor -> {
            supervisorRepository.delete(supervisor);
            return ResponseEntity.ok().build();
        }).orElseThrow(() ->  new ResourceNotFoundException("Supervisor not found with Id " + supervisorId));
    }
}
