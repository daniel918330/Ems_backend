package com.example.Ems.controller;

import lombok.AllArgsConstructor;
import com.example.Ems.dto.EmployeeDto;
import com.example.Ems.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    // Add Employee REST API with POST mapping and request body
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // Example of a simple test GET mapping
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Controller is working!", HttpStatus.OK);
    }

    // Build Get EmpLoyee REST API
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }
}