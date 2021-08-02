package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.Offer;
import softuni.exam.models.dtos.OfferSeedDto;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.OfferService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlValidator;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferImpl implements OfferService {
    private final String XML_INPUT_FILE = "src/main/resources/files/xml/offers.xml";
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final XmlValidator xmlValidator;
    private final CarRepository carRepository;
    private final ValidationUtil validationUtil;
    private final SellerRepository sellerRepository;

    public OfferImpl(OfferRepository offerRepository, ModelMapper modelMapper, XmlValidator xmlValidator, CarRepository carRepository, ValidationUtil validationUtil, SellerRepository sellerRepository) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.xmlValidator = xmlValidator;
        this.carRepository = carRepository;
        this.validationUtil = validationUtil;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public boolean areImported() {
        return offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(Path.of(XML_INPUT_FILE));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        OfferSeedDto offerSeedDto = xmlValidator.fromFile(XML_INPUT_FILE, OfferSeedDto.class);
        offerSeedDto.getOffers().stream().filter(d -> {
            boolean isValid = validationUtil.isValid(d);
            sb.append(isValid ? String.format("Successfully import offer %s - %s%n", d.getAddedOn(), d.isHasGoldStatus())
                    : String.format("Invalid offer%n"));
            return isValid;
        }).map(d -> {
            Offer offer = new Offer();
            offer.setDescription(d.getDescription());
            offer.setPrice(d.getPrice());
            offer.setAddedOn(LocalDateTime.parse(d.getAddedOn(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            offer.setHasGoldStatus(d.isHasGoldStatus());
            offer.setCar(carRepository.findById(d.getCar().getId()).orElse(null));
            offer.setSeller(sellerRepository.findById(d.getSeller().getId()).orElse(null));
            offer.setPictures(offer.getCar().getPictures());
            return offer;
        }).forEach(offerRepository::save);
        return sb.toString();
    }
}
