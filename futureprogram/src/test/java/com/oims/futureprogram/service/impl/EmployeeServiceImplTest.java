package com.oims.futureprogram.service.impl;

import com.oims.futureprogram.MockingObject.FakeObjectFactory;
import com.oims.futureprogram.model.Employee;
import com.oims.futureprogram.repository.EmployeeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepositoryMock;

    @Test
    public void getListEmployeeTest() {
        when(employeeRepositoryMock.findAll()).thenReturn(Arrays.asList(
                Employee.builder().id((long)1).build(),
                Employee.builder().id((long)2).build()
        ));

        List<Employee> employees = employeeService.getListEmployee();

        Assert.assertEquals(employees, employeeRepositoryMock.findAll());
    }

    @Test
    public void createEmployeeTest() {
        Employee employee = FakeObjectFactory.getFakeEmployee();

        when(employeeRepositoryMock.save(any(Employee.class))).thenReturn(employee);
        Employee returned = employeeService.createEmployee(employee);

        assertEquals(employee, returned);
    }
}