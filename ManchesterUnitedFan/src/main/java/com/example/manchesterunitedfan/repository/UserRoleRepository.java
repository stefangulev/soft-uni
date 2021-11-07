package com.example.manchesterunitedfan.repository;

import com.example.manchesterunitedfan.model.entities.UserRoleEntity;
import com.example.manchesterunitedfan.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    UserRoleEntity findByName(UserRoleEnum userRoleEnum);
}
