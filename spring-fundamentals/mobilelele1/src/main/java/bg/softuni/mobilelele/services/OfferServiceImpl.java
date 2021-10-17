package bg.softuni.mobilelele.services;

import bg.softuni.mobilelele.model.service.OfferCreateServiceModel;
import bg.softuni.mobilelele.model.entities.OfferEntity;
import bg.softuni.mobilelele.model.service.OfferUpdateServiceModel;
import bg.softuni.mobilelele.model.view.OfferDetailsView;
import bg.softuni.mobilelele.model.view.OfferSummaryView;
import bg.softuni.mobilelele.repositories.OfferRepository;
import bg.softuni.mobilelele.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class OfferServiceImpl implements OfferService{

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final ModelService modelService;

    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, UserService userService, ModelService modelService) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.modelService = modelService;
    }

    @Override
    public List<OfferSummaryView> getAllOffers() {
        return offerRepository.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public OfferDetailsView findOffer(Long id) {
       OfferEntity byId = offerRepository.findById(id).orElse(null);
        OfferDetailsView map = modelMapper.map(byId, OfferDetailsView.class);
        map.setSellerFullName(byId.getSeller().getFirstName() + " " + byId.getSeller().getLastName());
        return map;
    }

    @Override
    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }

    @Override
    public void updateOffer(OfferUpdateServiceModel updateServiceModel) {
        OfferEntity offerEntity = offerRepository.findById(updateServiceModel.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Offer with id " + updateServiceModel.getId() + " not found!"));
        offerEntity.setMileage(updateServiceModel.getMileage()).setYear(updateServiceModel.getYear())
                .setImageUrl(updateServiceModel.getImageUrl()).setDescription(updateServiceModel.getDescription())
                .setEngineType(updateServiceModel.getEngine()).setTransmission(updateServiceModel.getTransmission())
        .setPrice(BigDecimal.valueOf(updateServiceModel.getPrice()));
        offerRepository.save(offerEntity);
    }

    @Override
    public void addOffer(OfferCreateServiceModel serviceModel) {
        OfferEntity offerEntity = new OfferEntity()
                .setPrice(BigDecimal.valueOf(serviceModel.getPrice()))
                .setMileage(serviceModel.getMileage())
                .setYear(serviceModel.getYear())
                .setDescription(serviceModel.getDescription())
                .setImageUrl(serviceModel.getImageUrl())
                .setSeller(userService.findUserById(serviceModel.getSellerId()))
                .setModel(modelService.findModelByName(serviceModel.getModel()))
                .setEngineType(serviceModel.getEngine())
                .setTransmission(serviceModel.getTransmission());
        offerRepository.save(offerEntity);
    }

    private OfferSummaryView map(OfferEntity offerEntity) {
        OfferSummaryView summaryView = modelMapper.map(offerEntity, OfferSummaryView.class);
        summaryView.setModel(offerEntity.getModel().getName());
        return summaryView;
    }
}
