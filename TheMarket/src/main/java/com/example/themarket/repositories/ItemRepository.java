package com.example.themarket.repositories;

import com.example.themarket.model.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(value = "SELECT * FROM items WHERE owner_id = :ownerId" ,nativeQuery = true)
    Set<Item> findAllItemsByOwnerId(@Param(value = "ownerId") Long ownerId);
    @Query("SELECT i FROM Item i WHERE i.id = :id")
    Item findItemById(@Param(value = "id") Long id);
}
