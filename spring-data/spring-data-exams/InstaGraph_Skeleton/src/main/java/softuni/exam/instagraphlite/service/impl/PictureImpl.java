package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dtos.PictureSeedDto;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.logging.Logger;

@Service
public class PictureImpl implements PictureService {
    private final Logger logger;
    private final String JSON_FILE_PATH = "src/main/resources/files/pictures.json";
    private final Gson gson;
    private final PictureRepository pictureRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public PictureImpl(Logger logger, Gson gson, PictureRepository pictureRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.logger = logger;
        this.gson = gson;
        this.pictureRepository = pictureRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return pictureRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(JSON_FILE_PATH));
    }

    @Override
    public String importPictures() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson.fromJson(readFromFileContent(), PictureSeedDto[].class)).filter(d -> {
            boolean isValid = validationUtil.isValid(d) && pictureRepository.findPictureByPath(d.getPath()) == null;

            sb.append(isValid ? String.format("Successfully imported Picture, with size %.2f", d.getSize())
                    : "Invalid Picture").append(System.lineSeparator());
            return isValid;
        }).map(d -> modelMapper.map(d, Picture.class)).forEach(pictureRepository::save);
        return sb.toString();
    }

    @Override
    public String exportPictures() {
        StringBuilder sb = new StringBuilder();
        pictureRepository.findAllBySizeGreaterThanOrderBySizeAsc(30000.0)
                .forEach(p -> sb.append(String.format("%.2f - %s", p.getSize(), p.getPath())).append(System.lineSeparator()));
        return sb.toString();
    }
}
