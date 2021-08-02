package com.example.demo.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class TotalSalesByCustomerDto {
    @XmlElement(name = "customer")
    List<TotalSalesByCustomerSingleDto> customers;

    public List<TotalSalesByCustomerSingleDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<TotalSalesByCustomerSingleDto> customers) {
        this.customers = customers;
    }
}
