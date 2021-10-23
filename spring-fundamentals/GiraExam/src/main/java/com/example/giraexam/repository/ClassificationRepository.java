package com.example.giraexam.repository;

import com.example.giraexam.model.entities.Classification;
import com.example.giraexam.model.enums.ClassificationNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Long> {
    Classification findClassificationByName(ClassificationNameEnum classificationNameEnum);
}
