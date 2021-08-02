package softuni.exam.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sellers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerSeedDto {
    @XmlElement(name = "seller")
   private List<SellerSeedSingleDto> sellers;

    public List<SellerSeedSingleDto> getSellers() {
        return sellers;
    }

    public void setSellers(List<SellerSeedSingleDto> sellers) {
        this.sellers = sellers;
    }
}
