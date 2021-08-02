package hiberspring.repository;

import hiberspring.domain.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TownRepository extends JpaRepository<Town, Integer> {
    Town findTownByName(String name);

}
