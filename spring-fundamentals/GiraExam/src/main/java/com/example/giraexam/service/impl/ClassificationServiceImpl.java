package com.example.giraexam.service.impl;

import com.example.giraexam.model.entities.Classification;
import com.example.giraexam.model.enums.ClassificationNameEnum;
import com.example.giraexam.repository.ClassificationRepository;
import com.example.giraexam.service.ClassificationService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ClassificationServiceImpl implements ClassificationService {
   private final ClassificationRepository classificationRepository;

    public ClassificationServiceImpl(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    @Override
    public void initializeClassifications() {
        if(classificationRepository.count() == 0) {
            Arrays.stream(ClassificationNameEnum.values())
                    .forEach(v -> {
                        Classification classification =
                                new Classification().setName(v);
                        classificationRepository.save(classification);
                    });
        }
    }

    @Override
    public Classification findClassificationByName(ClassificationNameEnum classificationNameEnum) {
        return classificationRepository.findClassificationByName(classificationNameEnum);
    }
}
