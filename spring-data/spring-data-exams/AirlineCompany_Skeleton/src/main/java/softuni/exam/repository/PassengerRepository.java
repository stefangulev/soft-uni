package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import softuni.exam.models.entities.Passenger;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Passenger findPassengerByEmail(String email);
    @Query("SELECT p FROM Passenger p ORDER BY p.tickets.size DESC, p.email")
    List<Passenger> findPassengersOrderByTicketsCountDescendingThenByEmail();
    
}
