package com.example.demo.config;

import com.example.demo.models.dtos.*;
import com.example.demo.models.entities.*;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class ApplicationConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        Converter<String, LocalDateTime> stringToLocalDateTime = new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(MappingContext<String, LocalDateTime> mappingContext) {
                return LocalDateTime.parse(mappingContext.getSource());
            }
        };
        Converter<Set<Part>, Integer > setPartsToCount = new Converter<Set<Part>, Integer>() {
            @Override
            public Integer convert(MappingContext<Set<Part>, Integer> mappingContext) {
                return mappingContext.getSource().size();
            }
        };
        Converter<Set<Sale>, Integer> setSalesToCount = new Converter<Set<Sale>, Integer>() {
            @Override
            public Integer convert(MappingContext<Set<Sale>, Integer> mappingContext) {
                return mappingContext.getSource().size();
            }
        };
        Converter<Set<Sale>, BigDecimal> setSaleToTotalSpent = new Converter<Set<Sale>, BigDecimal>() {
            @Override
            public BigDecimal convert(MappingContext<Set<Sale>, BigDecimal> mappingContext) {
                List<Set<Part>> collect = mappingContext.getSource().stream().map(s -> s.getCar().getParts()).collect(Collectors.toList());
                return collect.stream().map(s -> s.stream().map(Part::getPrice).reduce(BigDecimal::add).orElse(null)).reduce(BigDecimal::add).orElse(null);
            }
        };
        Converter<Customer, String> customerToStringName = new Converter<Customer, String>() {
            @Override
            public String convert(MappingContext<Customer, String> mappingContext) {
                return mappingContext.getSource().getName();
            }
        };
        Converter<Car, BigDecimal> carToPrice = new Converter<Car, BigDecimal>() {
            @Override
            public BigDecimal convert(MappingContext<Car, BigDecimal> mappingContext) {
                return mappingContext.getSource().getParts().stream().map(Part::getPrice).reduce(BigDecimal::add).orElse(null);
            }
        };

        modelMapper.addConverter(stringToLocalDateTime);
        modelMapper.addConverter(setPartsToCount);
        modelMapper.addConverter(setSalesToCount);
        modelMapper.addConverter(setSaleToTotalSpent);
        modelMapper.addConverter(customerToStringName);
        modelMapper.addConverter(carToPrice);
       modelMapper.createTypeMap(CustomerSeedSingleDto.class, Customer.class).addMappings(m -> m.using(stringToLocalDateTime).map(CustomerSeedSingleDto::getBirthDate, Customer::setBirthDate));
       modelMapper.createTypeMap(Supplier.class, LocalSupplierSingleDto.class).addMappings(m -> m.using(setPartsToCount).map(Supplier::getParts, LocalSupplierSingleDto::setPartCount));
       modelMapper.createTypeMap(Customer.class, TotalSalesByCustomerSingleDto.class).addMappings(
               s -> s.using(setSaleToTotalSpent).map(Customer::getSales, TotalSalesByCustomerSingleDto::setSpentMoney))
       .addMappings(
                s -> s.using(setSalesToCount).map(Customer::getSales, TotalSalesByCustomerSingleDto::setBoughtCars));
       modelMapper.createTypeMap(Sale.class, SaleSingleDto.class).addMappings(
               s -> s.using(customerToStringName).map(Sale::getCustomer, SaleSingleDto::setCustomerName)).addMappings(
                       s -> s.using(carToPrice).map(Sale::getCar, SaleSingleDto::setPrice));
        return modelMapper;
    }
}
