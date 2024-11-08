package com.example.Ems.service.impl;

import com.example.Ems.exception.ResourceNotFoundException;
import com.example.Ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import com.example.Ems.dto.EmployeeDto;
import com.example.Ems.entity.Employee;
import com.example.Ems.mapper.EmployeeMapper;
import com.example.Ems.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("not fund : " + employeeId));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }
}

