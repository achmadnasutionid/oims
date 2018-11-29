package com.oims.futureprogram.controller;

import com.oims.futureprogram.model.Employee;
import com.oims.futureprogram.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public List<Employee> getEmployee() { return employeeService.getEmployee(); }

    @GetMapping("/employee/{employeeId}")
    public Optional<Employee> getEmployeeById(@PathVariable Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
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
