package com.example.pathfinder.repositories;

import com.example.pathfinder.model.Role;
import com.example.pathfinder.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(RoleEnum role);
}
