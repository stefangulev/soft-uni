package com.example.modelmapping.services;

import com.example.modelmapping.models.dtos.EmployeeDto;
import com.example.modelmapping.models.dtos.ManagerDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto findById(Long id);
    List<ManagerDto> findManagers();
    List<EmployeeDto> findEmployees();
}
