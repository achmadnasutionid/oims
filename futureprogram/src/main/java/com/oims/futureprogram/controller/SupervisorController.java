package com.oims.futureprogram.controller;

import com.oims.futureprogram.model.Supervisor;
import com.oims.futureprogram.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class SupervisorController {

    @Autowired
    private SupervisorService supervisorService;

    @GetMapping("/supervisor")
    public Page<Supervisor> getSupervisor(Pageable pageable) {
        return supervisorService.getSupervisor(pageable);
    }

    @GetMapping("/supervisor/{supervisorId}")
    public Supervisor getOneSupervisor(@PathVariable Long supervisorId) {
        return supervisorService.getOneSupervisor(supervisorId);
    }

    @PostMapping("/supervisor")
    public Supervisor createSupervisor(@Valid @RequestBody Supervisor supervisor) {
        return supervisorService.createSupervisor(supervisor);
    }

    @PutMapping("/supervisor/{supervisorId}")
    public Supervisor updateSupervisor(@PathVariable Long supervisorId, @Valid @RequestBody Supervisor supervisorRequest) {
        return supervisorService.updateSupervisor(supervisorId, supervisorRequest);
    }

    @DeleteMapping("/supervisor/{supervisorId}")
    public void deleteSupervisor(@PathVariable Long supervisorId) {
        supervisorService.deleteSupervisor(supervisorId);
    }
}
