package com.example.jsonprocessing.config;

import com.example.jsonprocessing.models.dtos.CategoryStatsDto;
import com.example.jsonprocessing.models.dtos.ProductWithoutBuyerDto;
import com.example.jsonprocessing.models.entities.Category;
import com.example.jsonprocessing.models.entities.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    }

}
