package hiberspring.service;

import hiberspring.domain.dtos.ProductSeedDto;
import hiberspring.domain.entities.Product;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.ProductRepository;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static hiberspring.common.Constants.PATH_TO_FILES;

@Service
public class ProductImpl implements ProductService{
    private final String XML_FILE_NAME = "products.xml";
    private final ProductRepository productRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final BranchRepository branchRepository;

    public ProductImpl(ProductRepository productRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil, BranchRepository branchRepository) {
        this.productRepository = productRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.branchRepository = branchRepository;
    }

    @Override
    public Boolean productsAreImported() {
        return productRepository.count() > 0;
    }

    @Override
    public String readProductsXmlFile() throws IOException {
        return fileUtil.readFile(PATH_TO_FILES + XML_FILE_NAME );
    }

    @Override
    public String importProducts() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        ProductSeedDto productSeedDto = xmlParser.parseXml(ProductSeedDto.class, PATH_TO_FILES + XML_FILE_NAME);
        productSeedDto.getProducts().stream().filter(p -> {
            boolean isValid = validationUtil.isValid(p);
            sb.append(isValid ? String.format("Successfully imported Product %s.", p.getName()) :
                    "Error: Invalid data.").append(System.lineSeparator());

            return isValid;
        }).map(p -> {
            Product product = modelMapper.map(p, Product.class);
            product.setBranch(branchRepository.findBranchByName(p.getBranch()));
            return product;
        }).forEach(productRepository::save);
        return sb.toString();
    }
}
