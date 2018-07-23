package com.example.demo.controller;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/es")
public class Employeecontroller {

    private EmployeeService service;

    @Autowired
    public Employeecontroller(EmployeeService service) {
        this.service = service;
    }

    @GetMapping(value = "/employees")
    public ResponseEntity<List<EmployeeDto>> getEmployee(){
        return new ResponseEntity<>(service.getEmployee(),HttpStatus.OK);
    }

    @PostMapping(value ="/employee")
    public ResponseEntity<EmployeeDto> add(@RequestBody EmployeeDto dto){
        return new ResponseEntity<>(service.addEmployee(dto),HttpStatus.CREATED);
    }
}
