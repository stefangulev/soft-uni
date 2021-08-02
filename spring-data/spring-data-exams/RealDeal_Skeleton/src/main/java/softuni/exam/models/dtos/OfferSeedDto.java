package softuni.exam.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "offers")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferSeedDto {
    @XmlElement(name = "offer")
    private List<OfferSeedSingleDto> offers;

    public List<OfferSeedSingleDto> getOffers() {
        return offers;
    }

    public void setOffers(List<OfferSeedSingleDto> offers) {
        this.offers = offers;
    }
}
