package hiberspring.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSeedDto {
    @XmlElement(name = "product")
    private List<ProductSeedSingleDto> products;

    public List<ProductSeedSingleDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductSeedSingleDto> products) {
        this.products = products;
    }
}
