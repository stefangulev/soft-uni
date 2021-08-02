package com.example.demo.repositories;

import com.example.demo.models.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c ORDER BY c.birthDate, c.youngDriver")
    List<Customer> findAllByBirthDateOrderByBirthdayAndYoungDriver();
    @Query("SELECT c FROM Customer c WHERE c.sales.size > 0")
    List<Customer> findAllWithMoreThanOneSale();
}
