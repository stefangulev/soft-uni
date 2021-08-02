package softuni.exam.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import softuni.exam.models.Car;
import softuni.exam.models.Picture;
import softuni.exam.models.dtos.CarSeedDto;
import softuni.exam.models.dtos.PictureSeedDto;
import softuni.exam.util.ValidationImpl;
import softuni.exam.util.ValidationUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Configuration
public class ApplicationBeanConfiguration {


    @Bean
    public Gson gson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    }


    @Bean
    public ModelMapper modelMapper() {
//        ModelMapper modelMapper = new ModelMapper();
//
//        Converter<String, LocalDate> stringToLocalDate = new Converter<String, LocalDate>() {
//            @Override
//            public LocalDate convert(MappingContext<String, LocalDate> mappingContext) {
//                return LocalDate.parse(mappingContext.getSource(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//            }
//        };
//        modelMapper.addConverter(stringToLocalDate);
//        modelMapper.createTypeMap(CarSeedDto.class, Car.class).addMappings(m -> m.using(stringToLocalDate).map(CarSeedDto::getRegisteredOn, Car::setRegisteredOn));
//        return modelMapper;
//
        return new ModelMapper();
    }

}
