package com.oims.futureprogram.service;

import com.oims.futureprogram.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    Page<Employee> getEmployee(Pageable pageable);

    Employee getOneEmployee(Long employeeId);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Long employeeId, Employee employeeRequest);

    void deleteEmployee(Long employeeId);
}
