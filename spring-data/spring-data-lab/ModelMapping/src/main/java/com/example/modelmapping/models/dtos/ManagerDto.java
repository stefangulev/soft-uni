package com.example.modelmapping.models.dtos;

import com.example.modelmapping.models.entities.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ManagerDto {
    private String firstName;
    private String lastName;
    private List<EmployeeDto> subordinates;

    public ManagerDto() {
        subordinates= new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<EmployeeDto> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<EmployeeDto> subordinates) {
        this.subordinates = subordinates;
    }

    @Override
    public String toString() {
        return String.format("Manager DTO - %s %s - %s", getFirstName(), getLastName(), getSubordinates().stream().map(EmployeeDto::toString).collect(Collectors.joining("\n")));
    }
}
