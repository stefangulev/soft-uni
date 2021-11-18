package com.example.manchesterunitedfan.repository;

import com.example.manchesterunitedfan.model.entities.StadiumVisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StadiumVisitRepository extends JpaRepository<StadiumVisitEntity, Long> {
    List<StadiumVisitEntity> findAllByOrderByDate();
    List<StadiumVisitEntity> findAllByUser_UsernameOrderByDate(String username);
    void deleteByDateBefore(LocalDateTime localDateTime);
}
