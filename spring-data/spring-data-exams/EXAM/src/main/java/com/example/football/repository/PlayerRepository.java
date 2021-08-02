package com.example.football.repository;

import com.example.football.models.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findPlayerByEmail(String email);
    @Query("SELECT p FROM Player p WHERE p.birthDate BETWEEN :startDate AND :endDate ORDER BY p.stat.shooting DESC, p.stat.passing DESC, p.stat.endurance DESC, p.lastName ")
    List<Player> findAllByBirthDateAfterAndBirthDateBeforeOrdOrderByStat(LocalDate startDate, LocalDate endDate);
}
