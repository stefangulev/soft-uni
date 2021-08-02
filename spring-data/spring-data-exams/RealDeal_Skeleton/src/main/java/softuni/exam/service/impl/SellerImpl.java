package softuni.exam.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.models.Seller;
import softuni.exam.models.dtos.SellerSeedDto;
import softuni.exam.models.dtos.SellerSeedSingleDto;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlValidator;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SellerImpl implements SellerService {
    private final String  XML_INPUT_FILE = "src/main/resources/files/xml/sellers.xml";
    private final SellerRepository sellerRepository;
    private final ValidationUtil validationUtil;
    private final XmlValidator xmlValidator;

    public SellerImpl(SellerRepository sellerRepository, ValidationUtil validationUtil, XmlValidator xmlValidator) {
        this.sellerRepository = sellerRepository;
        this.validationUtil = validationUtil;
        this.xmlValidator = xmlValidator;
    }

    @Override
    public boolean areImported() {
        return sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return Files.readString(Path.of(XML_INPUT_FILE));
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        SellerSeedDto sellerSeedDto = xmlValidator.fromFile(XML_INPUT_FILE, SellerSeedDto.class);
        sellerSeedDto.getSellers().stream().filter(s -> {
            boolean isValid = validationUtil.isValid(s);
            sb.append(isValid ? String.format("Successfully import seller %s - %s%n", s.getLastName(), s.getEmail())
                    : String.format("Invalid seller%n"));
            return isValid;
        }).map(d -> {
            Seller seller = new Seller();
            seller.setFirstName(d.getFirstName());
            seller.setLastName(d.getLastName());
            seller.setEmail(d.getEmail());
            seller.setRating(d.getRating());
            seller.setTown(d.getTown());
            return seller;
        }).forEach(sellerRepository::save);
        return sb.toString();

    }
}
