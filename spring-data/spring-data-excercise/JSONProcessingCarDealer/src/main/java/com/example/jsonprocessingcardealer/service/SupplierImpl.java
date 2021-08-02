package com.example.jsonprocessingcardealer.service;

import com.example.jsonprocessingcardealer.models.dtos.SupplierCatalogDto;
import com.example.jsonprocessingcardealer.models.dtos.SupplierSeedDto;
import com.example.jsonprocessingcardealer.models.entities.Supplier;
import com.example.jsonprocessingcardealer.repositories.SupplierRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class SupplierImpl implements SupplierService{

    private final String FILE_PATH = "src/main/resources/files/suppliers.json";
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public SupplierImpl(SupplierRepository supplierRepository, ModelMapper modelMapper, Gson gson) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }


    @Override
    public void seedSuppliers() throws IOException {
        if(supplierRepository.count() >0) {
            return;
        }
        Arrays.stream(gson.fromJson(Files.readString(Path.of(FILE_PATH)), SupplierSeedDto[].class))
                .map(d -> modelMapper.map(d, Supplier.class)).forEach(supplierRepository::save);
    }

    @Override
    public Supplier getRandom() {
        long randomId = ThreadLocalRandom.current().nextLong(1, supplierRepository.count() +1);
        return supplierRepository.findById(randomId).orElse(null);
    }

    @Override
    public List<SupplierCatalogDto> getLocalSuppliers() {
        return supplierRepository.findAllByImporterIsFalse().stream()
                .map(s -> modelMapper.map(s, SupplierCatalogDto.class)).collect(Collectors.toList());
    }
}
