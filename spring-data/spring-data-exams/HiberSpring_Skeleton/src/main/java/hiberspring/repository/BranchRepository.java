package hiberspring.repository;

import hiberspring.domain.entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Integer> {
    Branch findBranchByName(String name);
}
