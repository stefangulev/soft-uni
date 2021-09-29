package com.example.themarket.repositories;

import com.example.themarket.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = :username ")
    User findByUserName(@Param(value = "username") String username);
    @Query("SELECT u FROM User u WHERE u.id = :id ")
    User findByUserId(@Param(value = "id") Long id);
}
