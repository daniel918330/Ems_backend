package com.example.Ems.service;

import com.example.Ems.dto.EmployeeDto;
import com.example.Ems.entity.Employee;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long employeeId);
}
