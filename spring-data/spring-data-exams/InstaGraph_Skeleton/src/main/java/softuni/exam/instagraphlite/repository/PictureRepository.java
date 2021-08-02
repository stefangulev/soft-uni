package softuni.exam.instagraphlite.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.instagraphlite.models.entities.Picture;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Integer> {
    Picture findPictureByPath(String path);
    List<Picture> findAllBySizeGreaterThanOrderBySizeAsc(Double size);
}
