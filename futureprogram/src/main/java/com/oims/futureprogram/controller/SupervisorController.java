package com.oims.futureprogram.controller;

import com.oims.futureprogram.model.Response;
import com.oims.futureprogram.model.Supervisor;
import com.oims.futureprogram.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SupervisorController extends GlobalController {

    @Autowired
    private SupervisorService supervisorService;

    @GetMapping("/supervisor")
    public Response<List<Supervisor>> getListSupervisor() {
        return toResponse(supervisorService.getListSupervisor());
    }

    @GetMapping("/supervisor/{id}")
    public Response<Supervisor> getSupervisorById(@PathVariable Long id) {
        return toResponse(supervisorService.getSupervisorById(id));
    }

    @PostMapping("/supervisor")
    public Response<Supervisor> createSupervisor(@Valid @RequestBody Supervisor supervisor) {
        return toResponse(supervisorService.createSupervisor(supervisor));
    }

    @PutMapping("/supervisor/{supervisorId}")
    public Response<Supervisor> updateSupervisor(@PathVariable Long supervisorId, @Valid @RequestBody Supervisor supervisorRequest) {
        return toResponse(supervisorService.updateSupervisor(supervisorId, supervisorRequest));
    }

    @DeleteMapping("/supervisor/{supervisorId}")
    @Transactional
    public void deleteSupervisor(@PathVariable Long supervisorId) {
        supervisorService.deleteSupervisor(supervisorId);
    }

}
