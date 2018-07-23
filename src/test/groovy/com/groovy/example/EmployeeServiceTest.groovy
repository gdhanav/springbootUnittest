package com.groovy.example

import com.example.demo.dto.EmployeeDto
import com.example.demo.model.Employee
import com.example.demo.repository.EmployeeRepository
import com.example.demo.service.EmployeeService
import com.example.demo.service.impl.EmployeeServiceImpl
import org.modelmapper.ModelMapper
import spock.lang.Specification

class EmployeeServiceTest extends Specification implements EmployeeTrait{

    EmployeeService service
    EmployeeRepository repository
    ModelMapper mapper

    def setup(){
        repository = Mock(EmployeeRepository)
        mapper = Mock(ModelMapper)
        service = new EmployeeServiceImpl(repository,mapper)
    }


    def "get all employee test"(){
        given:
        List<EmployeeDto>  edto= createEmployeeDtos()
        List<Employee> employee = createEmployees()
        repository.findAll() >> employee
        mapper.map(_,_) >> edto
        when:
        List<EmployeeDto> employees = service.getEmployee()

        then:
         employees==edto

    }

    def "add employee test"(){
        given:
        EmployeeDto  edto= createEmployeeDto()
        Employee employee = createEmployee()
        mapper.map(employee,EmployeeDto.class) >> createEmployeeDto()
        repository.save(_)>> createEmployee()
        when:
        def emp=service.addEmployee(createEmployeeDto())
        then:
        emp==edto
    }
}
