package com.example.demo.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsWithPartsDto {
    @XmlElement(name = "car")
    List<CarsWithPartsSingleDto> cars;

    public List<CarsWithPartsSingleDto> getCars() {
        return cars;
    }

    public void setCars(List<CarsWithPartsSingleDto> cars) {
        this.cars = cars;
    }
}
