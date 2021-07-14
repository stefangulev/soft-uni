package com.example.modelmapping.models.dtos;

import java.math.BigDecimal;

public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private ManagerDto manager;

    public EmployeeDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public ManagerDto getManager() {
        return manager;
    }

    public void setManager(ManagerDto manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return String.format("Employee DTO - %s %s - %.2f - Manager: %s", getFirstName(), getLastName(), getSalary(), manager.getLastName());
    }
}
