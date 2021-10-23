package com.example.andreys_exam_db.repositories;

import com.example.andreys_exam_db.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findUserByUsernameAndEmail(String username, String email);
    User findUserByUsernameAndPassword(String username, String password);
}
