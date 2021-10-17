package bg.softuni.mobilelele.repositories;

import bg.softuni.mobilelele.model.entities.UserRoleEntity;
import bg.softuni.mobilelele.model.entities.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    UserRoleEntity findUserRoleEntityByRole(UserRoleEnum role);
}
