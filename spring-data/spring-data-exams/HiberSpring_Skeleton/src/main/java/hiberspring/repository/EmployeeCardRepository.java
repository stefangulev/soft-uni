package hiberspring.repository;

import hiberspring.domain.entities.Employee;
import hiberspring.domain.entities.EmployeeCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeCardRepository extends JpaRepository<EmployeeCard, Integer> {
    EmployeeCard findEmployeeCardByNumber(String number);

}
