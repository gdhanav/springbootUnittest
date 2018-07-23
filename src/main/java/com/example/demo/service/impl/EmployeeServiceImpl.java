package com.example.demo.service.impl;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private ModelMapper mapper;

    private EmployeeRepository repository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository,ModelMapper mapper) {
        this.repository=repository;
        this.mapper=mapper;
    }


    @Override
    public List<EmployeeDto> getEmployee() {
        java.lang.reflect.Type targetListType = new TypeToken<List<EmployeeDto>>() {}.getType();
        return mapper.map(repository.findAll(),targetListType);
    }



    @Override
    public EmployeeDto addEmployee(EmployeeDto employee) {
        Employee emp = mapper.map(employee,Employee.class);
        emp=repository.save(emp);
        return mapper.map(emp,EmployeeDto.class);

    }
}
