package com.example.modelmapping.repositories;

import com.example.modelmapping.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}
