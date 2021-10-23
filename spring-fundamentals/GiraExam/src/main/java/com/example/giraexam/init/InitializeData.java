package com.example.giraexam.init;

import com.example.giraexam.service.ClassificationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitializeData implements CommandLineRunner {
    private final ClassificationService classificationService;

    public InitializeData(ClassificationService classificationService) {
        this.classificationService = classificationService;
    }

    @Override
    public void run(String... args) throws Exception {
        classificationService.initializeClassifications();
    }
}
