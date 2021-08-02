package hiberspring.repository;

import hiberspring.domain.entities.Employee;
import hiberspring.domain.entities.EmployeeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findEmployeeByEmployeeCard_Number(String name);
    @Query("SELECT e FROM Employee e WHERE e.branch.products.size > 0 ORDER BY CONCAT(e.firstName, ' ', e.lastName) ASC, length(e.position) DESC")
    List<Employee> findEmployeesInBranchWithAtLeastOneProductOrderByFullNameAlphabeticallyLengthOfPositionDesc();

}
