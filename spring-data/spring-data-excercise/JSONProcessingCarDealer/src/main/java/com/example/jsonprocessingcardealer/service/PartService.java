package com.example.jsonprocessingcardealer.service;

import com.example.jsonprocessingcardealer.models.entities.Part;

import java.io.IOException;
import java.util.Set;

public interface PartService {
    void seedParts() throws IOException;

    Set<Part> getRandom();
}
