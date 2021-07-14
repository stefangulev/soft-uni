package com.example.modelmapping.services;

import com.example.modelmapping.models.dtos.EmployeeDto;
import com.example.modelmapping.models.dtos.ManagerDto;
import com.example.modelmapping.models.entities.Employee;
import com.example.modelmapping.repositories.EmployeeRepo;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeImpl implements EmployeeService {
    EmployeeRepo employeeRepo;
    ModelMapper modelMapper;

    public EmployeeImpl(EmployeeRepo repo) {
        this.employeeRepo = repo;
        this.modelMapper = new ModelMapper();
    }


    public EmployeeDto findById(Long id) {
        Employee employee = employeeRepo.findById(id).orElseThrow();
       return modelMapper.map(employee, EmployeeDto.class);
    }

    public List<ManagerDto> findManagers() {
       return employeeRepo.findAll().stream().map(m -> modelMapper.map(m, ManagerDto.class)).collect(Collectors.toList());
    }

    public List<EmployeeDto> findEmployees() {
//        Converter<Employee, String> converter = new AbstractConverter<Employee, String>() {
//            @Override
//            protected String convert(Employee employee) {
//               return employee.getManager() == null ? "no manager" : employee.getManager().getLastName();
//            }
//        };

        PropertyMap<Employee, EmployeeDto> employeeMap = new PropertyMap<Employee, EmployeeDto>() {
            @Override
            protected void configure() {
                map().setFirstName(source.getFirstName());
                map().setLastName(source.getLastName());
                map().setSalary(source.getSalary());
                if (source.getManager() != null) {
                    map().getManager().setLastName(source.getManager().getLastName());
                }
            }
        };
        List<Employee> all = employeeRepo.findAll();
        modelMapper.addMappings(employeeMap);
       return all.stream().map(e -> modelMapper.map(e, EmployeeDto.class)).collect(Collectors.toList());


    }





}
