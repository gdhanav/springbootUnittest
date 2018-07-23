package com.groovy.example

import com.example.demo.controller.Employeecontroller
import com.example.demo.dto.EmployeeDto
import com.example.demo.service.EmployeeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class EmployeeControllerTest extends Specification implements EmployeeTrait{

    Employeecontroller controller
    EmployeeService service

    def setup(){
        service = Mock(EmployeeService)
        controller = new Employeecontroller(service)
    }


    def "get employee controller test"(){
        given:
        ResponseEntity responseEntity
        List<EmployeeDto> edto= createEmployeeDtos()
        service.getEmployee()>> edto
        when:
       responseEntity = controller.getEmployee()
        then:
        HttpStatus.OK==responseEntity.statusCode
        edto==responseEntity.body


    }

    def "add employee controller test"(){
        given:
        ResponseEntity responseEntity
        EmployeeDto dto = createEmployeeDto()
        service.addEmployee(dto) >> dto
        when:
        responseEntity = controller.add(dto)
        then:
        HttpStatus.CREATED==responseEntity.statusCode
        dto==responseEntity.body

    }
}
