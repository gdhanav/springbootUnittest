package com.example.demo.service;

import com.example.demo.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> getEmployee();
   EmployeeDto addEmployee(EmployeeDto employee);

}
