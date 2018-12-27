package com.oims.futureprogram.controller;

import com.oims.futureprogram.model.Response;
import com.oims.futureprogram.model.Employee;
import com.oims.futureprogram.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController extends GlobalController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public Response<List<Employee>> getListEmployee() { return toResponse(employeeService.getListEmployee()); }

    @GetMapping("/employee/{id}")
    public Response<Employee> getEmployeeById(@PathVariable Long id) {
        return toResponse(employeeService.getEmployeeById(id));
    }

    @PostMapping("/employee")
    public Response<Employee> createEmployee(@Valid @RequestBody Employee employee) { return toResponse(employeeService.createEmployee(employee)); }

    @PutMapping("/employee/{employeeId}")
    public Response<Employee> updateEmployee(@PathVariable Long employeeId, @Valid @RequestBody Employee employeeRequest) {
        return toResponse(employeeService.updateEmployee(employeeId, employeeRequest));
    }

    @DeleteMapping("/employee/{employeeId}")
    @Transactional
    public void deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

}
