package com.example.jsonprocessingcardealer.service;

import com.example.jsonprocessingcardealer.models.dtos.CustomerSeedDto;
import com.example.jsonprocessingcardealer.models.dtos.PresentCustomerDto;
import com.example.jsonprocessingcardealer.models.entities.Customer;
import com.example.jsonprocessingcardealer.repositories.CustomerRepository;
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
public class CustomerImpl implements CustomerService{

    private final String FILE_PATH = "src/main/resources/files/customers.json";
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public CustomerImpl(CustomerRepository customerRepository, ModelMapper modelMapper, Gson gson) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }


    @Override
    public void seedCustomers() throws IOException {
        if(customerRepository.count() > 0) {
            return;
        }
        Arrays.stream(gson.fromJson(Files.readString(Path.of(FILE_PATH)), CustomerSeedDto[].class))
                .map(d -> modelMapper.map(d, Customer.class)).forEach(customerRepository::save);
    }

    @Override
    public Customer getRandom() {
        long randomId = ThreadLocalRandom.current().nextLong(1, customerRepository.count() + 1);
        return customerRepository.findById(randomId).orElse(null);
    }

    @Override
    public List<PresentCustomerDto> findAllCustomersOrderedByBirthday() {
       return customerRepository.findAllOrderByBirthDateAndAndYoungDriver().stream()
                .map(c -> modelMapper.map(c, PresentCustomerDto.class)).collect(Collectors.toList());

    }
}
