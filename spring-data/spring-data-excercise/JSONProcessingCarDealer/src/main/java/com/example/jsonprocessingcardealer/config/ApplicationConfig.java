package com.example.jsonprocessingcardealer.config;

import com.example.jsonprocessingcardealer.models.dtos.CustomerSeedDto;
import com.example.jsonprocessingcardealer.models.dtos.SupplierCatalogDto;
import com.example.jsonprocessingcardealer.models.entities.Customer;
import com.example.jsonprocessingcardealer.models.entities.Part;
import com.example.jsonprocessingcardealer.models.entities.Supplier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        Converter<String, LocalDateTime> toLocalDateTime = new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(MappingContext<String, LocalDateTime> mappingContext) {
                return LocalDateTime.parse(mappingContext.getSource());
            }
        };
        modelMapper.addConverter(toLocalDateTime);
        Converter<Set<Part>, Integer> toCount = new Converter<Set<Part>, Integer>() {
            @Override
            public Integer convert(MappingContext<Set<Part>, Integer> mappingContext) {
                return mappingContext.getSource().size();
            }
        };
        modelMapper.addConverter(toCount);

        modelMapper.createTypeMap(CustomerSeedDto.class, Customer.class)
                .addMappings(mapper -> mapper.map(CustomerSeedDto::getBirthDate, Customer::setBirthDate));
        modelMapper.createTypeMap(Supplier.class, SupplierCatalogDto.class)
                .addMappings(mapper -> mapper.using(toCount).map(Supplier::getParts, SupplierCatalogDto::setPartsCount));

        return modelMapper;
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    }

}
