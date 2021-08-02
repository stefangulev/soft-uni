package com.example.demo.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocalSupplierDto {
    @XmlElement(name = "supplier")
    List<LocalSupplierSingleDto> suppliers;

    public List<LocalSupplierSingleDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<LocalSupplierSingleDto> suppliers) {
        this.suppliers = suppliers;
    }
}
