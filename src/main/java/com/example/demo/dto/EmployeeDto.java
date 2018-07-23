package com.example.demo.dto;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Builder
@EqualsAndHashCode
@ToString
public class EmployeeDto {


    private String id;
    private String name;
    private String dob;
    private String doj;
    private String location;
    private String team;
}
