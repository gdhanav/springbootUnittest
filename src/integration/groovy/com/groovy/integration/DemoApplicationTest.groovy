package com.groovy.integration

import com.example.demo.DemoApplication
import com.example.demo.dto.EmployeeDto
import com.example.demo.service.EmployeeService
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import spock.lang.Specification

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,classes = DemoApplication)
class DemoApplicationTest extends Specification implements EmployeeTrait{

    @Autowired
    EmployeeService service

    @LocalServerPort
    int port

    def setup(){
        RestAssured.baseURI = "http://localhost"
        RestAssured.port = port
        RestAssured.basePath = "/es"
    }

    def "get All employees"(){
        given:
        Response response
        service.addEmployee(createEmployeeDto())
        when:
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/employees")
        then:
        HttpStatus.OK.value()==response.statusCode
        response.jsonPath().getList("",EmployeeDto.class).contains(createEmployeeDto())
    }

   /* def "get All employees"(){
        RestAssured.given(
                service.addEmployee(createEmployeeDto())
        )
        .when()
        .get("/employees")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id",Matchers.hasItems("1"))
    }*/


}
