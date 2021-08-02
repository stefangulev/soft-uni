package com.example.jsonprocessingcardealer.repositories;

import com.example.jsonprocessingcardealer.models.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c ORDER BY c.birthDate, c.youngDriver")
    List<Customer> findAllOrderByBirthDateAndAndYoungDriver();
}
