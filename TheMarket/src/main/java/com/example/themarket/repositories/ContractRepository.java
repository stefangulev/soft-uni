package com.example.themarket.repositories;

import com.example.themarket.model.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    @Query("SELECT c FROM Contract c WHERE c.item.id = :itemId")
    Contract findContractByItemId(@Param(value = "itemId") Long itemId);
    @Query(value = "SELECT * FROM contracts WHERE active is true ORDER BY price ASC", nativeQuery = true)
    Set<Contract> findAllByActiveTrue();
}
