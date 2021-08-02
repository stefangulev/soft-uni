package com.example.demo.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarSeedDto {
    @XmlElement(name = "car")
    List<CarSeedSingleDto> cars;

    public List<CarSeedSingleDto> getCars() {
        return cars;
    }

    public void setCars(List<CarSeedSingleDto> cars) {
        this.cars = cars;
    }
}
