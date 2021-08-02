package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entities.Plane;

public interface PlaneRepository  extends JpaRepository<Plane, Long> {
    Plane findPlaneByRegisterNumber(String registerNumber);
}
