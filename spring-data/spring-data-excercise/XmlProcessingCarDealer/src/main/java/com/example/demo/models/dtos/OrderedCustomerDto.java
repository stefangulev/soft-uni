package com.example.demo.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderedCustomerDto {
    @XmlElement(name = "customer")
    List<OrderedCustomerSingleDto> customers;

    public List<OrderedCustomerSingleDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<OrderedCustomerSingleDto> customers) {
        this.customers = customers;
    }
}
