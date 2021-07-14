package com.example.modelmapping.services;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class ConsoleRunner implements CommandLineRunner {

    EmployeeService employeeService;

    public ConsoleRunner(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println(employeeService.findById(1L));
//        employeeService.findManagers().forEach(System.out::println);
        employeeService.findEmployees().forEach(System.out::println);
    }
}
