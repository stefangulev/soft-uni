package com.example.jsonprocessingcardealer.service;

import com.example.jsonprocessingcardealer.models.dtos.PartSeedDto;
import com.example.jsonprocessingcardealer.models.entities.Part;
import com.example.jsonprocessingcardealer.repositories.PartRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class PartImpl implements PartService{
    private final String FILE_PATH = "src/main/resources/files/parts.json";
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final PartRepository partRepository;
    private final SupplierService supplierService;


    public PartImpl(ModelMapper modelMapper, Gson gson, PartRepository partRepository, SupplierService supplierService) {
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.partRepository = partRepository;
        this.supplierService = supplierService;
    }


    @Override
    public void seedParts() throws IOException {
        if(partRepository.count() > 0) {
            return;
        }
        Arrays.stream(gson.fromJson(Files.readString(Path.of(FILE_PATH)), PartSeedDto[].class))
                .map(p -> {

                    Part part = modelMapper.map(p, Part.class);
                    part.setSupplier(supplierService.getRandom());
                    return part;
                }).forEach(partRepository::save);
    }

    @Override
    public Set<Part> getRandom() {
        int randomPartsCount = ThreadLocalRandom.current().nextInt(3, 6);
        long partsCount = partRepository.count();
        Set<Part> randomParts = new HashSet<>();
        for (int i = 0; i < randomPartsCount ; i++) {
            long randomId = ThreadLocalRandom.current().nextLong(1,  partsCount+ 1);
           randomParts.add(partRepository.findById(randomId).orElse(null));
        }
        return randomParts;
    }
}
