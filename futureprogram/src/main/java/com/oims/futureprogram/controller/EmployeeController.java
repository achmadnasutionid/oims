package com.oims.futureprogram.controller;

import com.oims.futureprogram.exception.ResourceNotFoundException;
import com.oims.futureprogram.model.Employee;
import com.oims.futureprogram.repository.EmployeeRepository;
import com.oims.futureprogram.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public Page<Employee> getEmployee(Pageable pageable) { return employeeService.getEmployee(pageable); }

    @GetMapping("/employee/{employeeId}")
    public Employee getOneEmployee(@PathVariable Long employeeId) {
        return employeeService.getOneEmployee(employeeId);
    }

    @PostMapping("/employee")
    public Employee createEmployee(@Valid @RequestBody Employee employee) { return employeeService.createEmployee(employee); }

    @PutMapping("/employee/{employeeId}")
    public Employee updateEmployee(@PathVariable Long employeeId, @Valid @RequestBody Employee employeeRequest) {
        return employeeService.updateEmployee(employeeId, employeeRequest);
    }

    @DeleteMapping("/employee/{employeeId}")
    public void deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}
