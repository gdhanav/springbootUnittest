package com.groovy.example

import com.example.demo.dto.EmployeeDto
import com.example.demo.model.Employee

import java.text.SimpleDateFormat

trait EmployeeTrait {

    List<EmployeeDto> edtos
    List<Employee> employees

    def createEmployeeDtos(){
        edtos = new ArrayList<>()
        edtos.add(createEmployeeDto())
        return edtos
    }
    def createEmployees(){
        employees = new ArrayList<>()
        employees.add(createEmployee())
        return employees
    }

    def createEmployeeDto(){
        EmployeeDto dto = new EmployeeDto()
        dto.setId("1")
        dto.setName("sam")
        dto.setDob("19/07/2018")
        dto.setDoj("19/07/2018")
        dto.setLocation("NSW")
        dto.setTeam("UX")
        return dto
    }

    def createEmployee(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy")
        Employee employee = new Employee()
        employee.setId(1)
        employee.setName("sam")
        employee.setDob(format.parse("19/07/2018"))
        employee.setDoj(format.parse("19/07/2018"))
        employee.setLocation("NSW")
        employee.setTeam("UX")

        return employee
    }


}