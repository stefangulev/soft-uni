package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.Picture;
import softuni.exam.models.dtos.PictureSeedDto;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.PictureService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PictureImpl implements PictureService {
    private final String JSON_FILE_PATH = "src/main/resources/files/json/pictures.json";
    private final PictureRepository pictureRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final CarRepository carRepository;

    public PictureImpl(PictureRepository pictureRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, CarRepository carRepository) {
        this.pictureRepository = pictureRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.carRepository = carRepository;
    }


    @Override
    public boolean areImported() {
        return pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        return Files.readString(Path.of(JSON_FILE_PATH));
    }

    @Override
    public String importPictures() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson.fromJson(readPicturesFromFile(), PictureSeedDto[].class)).filter(d -> {
            boolean isValid = validationUtil.isValid(d);
            sb.append(isValid ? String.format("Successfully import picture - %s%n", d.getName()) :
                    String.format("Invalid picture%n"));
            return isValid;
        }).map(d -> {
            Picture picture = new Picture();
            picture.setName(d.getName());
            picture.setDateAndTime(LocalDateTime.parse(d.getDateAndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            picture.setCar(carRepository.findById(d.getCar()).orElse(null));
            return picture;
        }).forEach(p -> pictureRepository.save(p));
        return sb.toString();
    }
}
