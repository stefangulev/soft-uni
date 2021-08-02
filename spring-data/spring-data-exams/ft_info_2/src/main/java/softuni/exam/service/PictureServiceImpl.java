package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dtos.PictureSeedDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.repository.PictureRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlValidatorUtil;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {
    private final String XML_FILE_PATH = "src/main/resources/files/xml/pictures.xml";
    private final XmlValidatorUtil xmlValidatorUtil;
    private final PictureRepository pictureRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;

    public PictureServiceImpl(XmlValidatorUtil xmlValidatorUtil, PictureRepository pictureRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper, FileUtil fileUtil) {
        this.xmlValidatorUtil = xmlValidatorUtil;
        this.pictureRepository = pictureRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
    }

    @Override
    public String importPictures() throws JAXBException, IOException {
        StringBuilder sb = new StringBuilder();
        PictureSeedDto pictureSeedDto = xmlValidatorUtil.fromFile(XML_FILE_PATH, PictureSeedDto.class);
      pictureSeedDto.getPictures().stream().filter(d -> {
            boolean isValid = validatorUtil.isValid(d);
            sb.append(isValid ? String.format("Successfully imported picture - %s", d.getUrl()) : "Invalid picture")
                    .append(System.lineSeparator());
            return isValid;
        }).map(d -> modelMapper.map(d, Picture.class)).forEach(pictureRepository::save);
        return sb.toString();
    }

    @Override
    public boolean areImported() {
        return pictureRepository.count() >0;
    }

    @Override
    public String readPicturesXmlFile() throws IOException {
       return fileUtil.readFile(XML_FILE_PATH);
    }

}
