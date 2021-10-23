package com.example.giraexam.service;

import com.example.giraexam.model.entities.Classification;
import com.example.giraexam.model.enums.ClassificationNameEnum;

public interface ClassificationService {
    void initializeClassifications();
    Classification findClassificationByName(ClassificationNameEnum classificationNameEnum);
}
