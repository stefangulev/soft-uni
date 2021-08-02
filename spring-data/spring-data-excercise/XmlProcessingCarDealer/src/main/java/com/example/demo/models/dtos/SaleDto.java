package com.example.demo.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleDto {
    @XmlElement(name = "sale")
    List<SaleSingleDto> sales;

    public List<SaleSingleDto> getSales() {
        return sales;
    }

    public void setSales(List<SaleSingleDto> sales) {
        this.sales = sales;
    }
}
