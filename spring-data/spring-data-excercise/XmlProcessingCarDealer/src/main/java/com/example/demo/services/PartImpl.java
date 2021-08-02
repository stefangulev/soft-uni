package com.example.demo.services;

import com.example.demo.models.dtos.PartSeedDto;
import com.example.demo.models.entities.Part;
import com.example.demo.repositories.PartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PartImpl implements PartService {

    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final SupplierService supplierService;

    public PartImpl(PartRepository partRepository, ModelMapper modelMapper, SupplierService supplierService) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.supplierService = supplierService;
    }


    @Override
    public void seedParts(PartSeedDto partSeedDto) {
        partSeedDto.getParts().stream()
                .map(d -> {
                    Part part = modelMapper.map(d, Part.class);
                    part.setSupplier(supplierService.getRandom());
                    return part;
                }).forEach(partRepository::save);
    }

    @Override
    public Long getCount() {
        return partRepository.count();
    }

    @Override
    public Set<Part> getRandom() {
        int randomPartCount = ThreadLocalRandom.current().nextInt(10, 21);
        long totalPartsCount = partRepository.count();
        Set<Part> parts = new HashSet<>();

        for (int i = 0; i < randomPartCount; i++) {
            long randomId = ThreadLocalRandom.current().nextLong(1, totalPartsCount);
            parts.add(partRepository.findById(randomId).orElse(null));
        }
        return parts;
    }
}
