package com.oims.futureprogram.service;

import com.oims.futureprogram.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getListEmployee();

    Optional<Employee> getEmployeeById(Long id);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Long employeeId, Employee employeeRequest);

    void deleteEmployee(Long employeeId);
}
