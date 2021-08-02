package com.example.demo.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerSeedDto {
    @XmlElement(name = "customer")
    private List<CustomerSeedSingleDto> customers;

    public List<CustomerSeedSingleDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerSeedSingleDto> customers) {
        this.customers = customers;
    }
}
