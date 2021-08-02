package com.example.demo.models.dtos;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderedCustomerSingleDto {
    @XmlElement(name = "id")
    private Long id;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "birth-date")
    private String birthDate;
    @XmlElement(name = "is-young-driver")
    boolean isYoungDriver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
