package com.oims.futureprogram.service.impl;

import com.oims.futureprogram.error.ErrorCode;
import com.oims.futureprogram.exception.ResourceNotFoundException;
import com.oims.futureprogram.model.Employee;
import com.oims.futureprogram.repository.EmployeeRepository;
import com.oims.futureprogram.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getEmployee() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long employeeId) {
        if(!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        }
        return employeeRepository.findById(employeeId);
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
        }).orElseThrow(() -> new ResourceNotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
    }

    public void deleteEmployee(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        }
        employeeRepository.deleteEmployeeById(employeeId);
    }
}