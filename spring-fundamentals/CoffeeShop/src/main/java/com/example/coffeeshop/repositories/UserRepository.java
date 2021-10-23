package com.example.coffeeshop.repositories;

import com.example.coffeeshop.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsernameOrEmail(String username, String email);
    User findUserByUsernameAndPassword(String username, String password);
    @Query("SELECT u from User u order by size(u.orders) DESC")
    List<User> findAllUsersOrderedByCountDesc();
}
