package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entities.Town;

public interface TownRepository extends JpaRepository<Town, Long> {
    Town findTownByName(String name);

}
