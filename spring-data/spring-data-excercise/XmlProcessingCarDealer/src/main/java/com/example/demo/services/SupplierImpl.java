package com.example.demo.services;

import com.example.demo.models.dtos.LocalSupplierDto;
import com.example.demo.models.dtos.LocalSupplierSingleDto;
import com.example.demo.models.dtos.SupplierSeedDto;
import com.example.demo.models.entities.Supplier;
import com.example.demo.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class SupplierImpl implements SupplierService{
    private final String FILE_PATH_NAME = "src/main/resources/files/suppliers.xml";
   private final SupplierRepository supplierRepository;
   private final ModelMapper modelMapper;

    public SupplierImpl(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void seedSuppliers(SupplierSeedDto supplierSeedDto) {
        supplierSeedDto.getSuppliers().stream()
                .map(d -> modelMapper.map(d, Supplier.class)).forEach(supplierRepository::save);

    }

    @Override
    public Long getCount() {
        return supplierRepository.count();
    }

    @Override
    public Supplier getRandom() {
        long randomId = ThreadLocalRandom.current().nextLong(1, supplierRepository.count() + 1);
        return supplierRepository.findById(randomId).orElse(null);
    }

    @Override
    public LocalSupplierDto findLocalSuppliers() {
        List<LocalSupplierSingleDto> localSuppliers = supplierRepository.findAllByImporterIsFalse().stream().map(s -> modelMapper.map(s, LocalSupplierSingleDto.class)).collect(Collectors.toList());
       LocalSupplierDto localSupplierDto = new LocalSupplierDto();
       localSupplierDto.setSuppliers(localSuppliers);
       return localSupplierDto;
    }
}
