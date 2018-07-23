package com.example.demo.configuration;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.model.Employee;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper= new ModelMapper();
        mapper.addMappings(employeeDtoMap);
        mapper.addMappings(employeeMap);
        return mapper;
    }


    Converter<String,Date> stringToDateConverter = new Converter<String, Date>() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        @Override
        public Date convert(MappingContext<String, Date> context) {
            if(context.getSource()!=null) {
                try {
                    return format.parse(context.getSource());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    };

    Converter<Date,String> dateToStringConverter = new Converter<Date, String>() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        @Override
        public String convert(MappingContext<Date, String> context) {
            if(context.getSource()!=null) {

                return format.format(context.getSource());

            }
            return null;
        }
    };

    Converter<Long,String> longToStringConverter = new Converter<Long, String>() {
        @Override
        public String convert(MappingContext<Long, String> context) {
            if(context.getSource()!=null){
                return String.valueOf(context.getSource());
            }
            return null;
        }
    };

    Converter<String,Long> stringTolongConverter = new Converter<String, Long>() {
        @Override
        public Long convert(MappingContext<String, Long> context) {
            if(context.getSource()!=null){
                return Long.valueOf(context.getSource());
            }
            return null;
        }
    };


    PropertyMap<Employee,EmployeeDto> employeeDtoMap = new PropertyMap<Employee, EmployeeDto>() {
        @Override
        protected void configure() {
            using(dateToStringConverter).map(source.getDob()).setDob(null);
            using(dateToStringConverter).map(source.getDoj()).setDoj(null);
            using(longToStringConverter).map(source.getId()).setId(null);
        }
    };

    PropertyMap<EmployeeDto,Employee> employeeMap = new PropertyMap<EmployeeDto, Employee>() {
        @Override
        protected void configure() {
            using(stringToDateConverter).map(source.getDob()).setDob(null);
            using(stringToDateConverter).map(source.getDoj()).setDoj(null);
            using(stringTolongConverter).map(source.getId()).setId(0);
        }
    };
}
