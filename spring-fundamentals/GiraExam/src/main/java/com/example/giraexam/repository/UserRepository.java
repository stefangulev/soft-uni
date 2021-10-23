package com.example.giraexam.repository;

import com.example.giraexam.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsernameOrEmail(String username, String email);
    User findUserByEmailAndPassword(String email, String password);
}
