package com.example.demo.config;


import com.example.demo.models.dtos.AddGameDto;
import com.example.demo.models.entities.Game;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.io.BufferedReader;
import java.io.InputStreamReader;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<AddGameDto, Game>() {
            @Override
            protected void configure() {
                map().setImageThumbnail(source.getThubnailURL());
            }
        });

        return modelMapper;
    }

    @Bean
    public BufferedReader bufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
