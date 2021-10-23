package com.example.mymusic.repositories;

import com.example.mymusic.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsernameOrEmail(String username, String email);
    User findUserByUsernameAndPassword(String username, String password);
}
