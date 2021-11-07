package bg.softuni.mobilelele.services;

import bg.softuni.mobilelele.model.service.OfferCreateServiceModel;
import bg.softuni.mobilelele.model.service.OfferUpdateServiceModel;
import bg.softuni.mobilelele.model.view.OfferDetailsView;
import bg.softuni.mobilelele.model.view.OfferSummaryView;

import java.security.Principal;
import java.util.List;

public interface OfferService {
    List<OfferSummaryView> getAllOffers();
    OfferDetailsView findOffer(Long id);
    void deleteOffer(Long id);
    void updateOffer(OfferUpdateServiceModel updateServiceModel);

    void addOffer(OfferCreateServiceModel serviceModel, MobileleleUser user);
}
