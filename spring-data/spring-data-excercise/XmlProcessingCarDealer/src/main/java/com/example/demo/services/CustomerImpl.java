package com.example.demo.services;

import com.example.demo.models.dtos.*;
import com.example.demo.models.entities.Customer;
import com.example.demo.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CustomerImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void seedCustomers(CustomerSeedDto customerSeedDto) {
      customerSeedDto.getCustomers().stream()
              .map(d -> modelMapper.map(d, Customer.class)).forEach(customerRepository::save);

    }

    @Override
    public Long getCount() {
        return customerRepository.count();
    }

    @Override
    public Customer getRandom() {
        long randomId = ThreadLocalRandom.current().nextLong(1, customerRepository.count() + 1);
        return customerRepository.findById(randomId).orElse(null);
    }

    @Override
    public OrderedCustomerDto getOrderedCustomersByBirthDate() {
        List<OrderedCustomerSingleDto> collect = customerRepository.findAllByBirthDateOrderByBirthdayAndYoungDriver().stream()
                .map(c -> modelMapper.map(c, OrderedCustomerSingleDto.class)).collect(Collectors.toList());
        OrderedCustomerDto orderedCustomerDto = new OrderedCustomerDto();
        orderedCustomerDto.setCustomers(collect);
        return orderedCustomerDto;
    }

    @Override
    public TotalSalesByCustomerDto getTotalSalesByCustomer() {
        List<TotalSalesByCustomerSingleDto> totalSalesPerCustomer = customerRepository.findAllWithMoreThanOneSale().stream()
                .map(c -> modelMapper.map(c, TotalSalesByCustomerSingleDto.class)).sorted((l ,r) -> {
                    int result = r.getSpentMoney().compareTo(l.getSpentMoney());
                    if (result == 0) {
                        result = Integer.compare(r.getBoughtCars(), l.getBoughtCars());
                    }
                    return result;
                }).collect(Collectors.toList());
        TotalSalesByCustomerDto totalSalesByCustomerDto = new TotalSalesByCustomerDto();
        totalSalesByCustomerDto.setCustomers(totalSalesPerCustomer);
        return totalSalesByCustomerDto;
    }
}
