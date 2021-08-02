package com.example.demo.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierSeedDto {
    @XmlElement(name = "supplier")
    List<SupplierSeedSingleDto> suppliers;

    public List<SupplierSeedSingleDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierSeedSingleDto> suppliers) {
        this.suppliers = suppliers;
    }
}
