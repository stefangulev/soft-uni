package com.example.springdataintrolab.repositories;

import com.example.springdataintrolab.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountById(Long id);
}
