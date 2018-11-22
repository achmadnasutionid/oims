package com.oims.futureprogram.service.impl;

import com.oims.futureprogram.exception.ResourceNotFoundException;
import com.oims.futureprogram.model.Employee;
import com.oims.futureprogram.repository.EmployeeRepository;
import com.oims.futureprogram.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Page<Employee> getEmployee(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Employee getOneEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found with Id " + employeeId));
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long employeeId, Employee employeeRequest) {
        return employeeRepository.findById(employeeId).map(employee -> {
            employee.setNama(employeeRequest.getNama());
            employee.setHp(employeeRequest.getHp());
            employee.setEmail(employeeRequest.getEmail());
            return employeeRepository.save(employee);
        }).orElseThrow(() -> new ResourceNotFoundException("Employee not found with Id " + employeeId));
    }

    public void deleteEmployee(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee not found with Id " + employeeId);
        }
        Employee employee = getOneEmployee(employeeId);
        employeeRepository.delete(employee);
    }
}