package com.oims.futureprogram.controller;

import com.oims.futureprogram.exception.ResourceNotFoundException;
import com.oims.futureprogram.model.Employee;
import com.oims.futureprogram.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employee")
    public Page<Employee> getEmployee(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @PostMapping("/employee")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public Employee updateEmployee(@PathVariable Long employeeId, @Valid @RequestBody Employee employeeRequest) {
        return employeeRepository.findById(employeeId).map(employee -> {
            employee.setNama(employeeRequest.getNama());
            employee.setHp(employeeRequest.getHp());
            employee.setEmail(employeeRequest.getEmail());
            return employeeRepository.save(employee);
        }).orElseThrow(() -> new ResourceNotFoundException("Employee not found with Id " + employeeId));
    }

    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId) {
        return employeeRepository.findById(employeeId).map(employee -> {
            employeeRepository.delete(employee);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Employee not found with Id " + employeeId));
    }
}
