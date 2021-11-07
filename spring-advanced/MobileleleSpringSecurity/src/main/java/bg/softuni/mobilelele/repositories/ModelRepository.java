package bg.softuni.mobilelele.repositories;

import bg.softuni.mobilelele.model.entities.ModelEntity;
import bg.softuni.mobilelele.model.view.AddOfferModelView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {
    Optional<ModelEntity> findByName(String name);
}
