package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.domain.entities.Player;

import java.math.BigDecimal;
import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    List<Player> findAllByTeam_NameOrderById(String teamName);
    List<Player> findAllBySalaryGreaterThanOrderBySalaryDesc(BigDecimal number);

}
