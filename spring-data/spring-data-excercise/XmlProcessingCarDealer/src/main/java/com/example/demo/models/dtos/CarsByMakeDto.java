package com.example.demo.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsByMakeDto {
    @XmlElement(name = "car")
    List<CarsByMakeSingleDto> cars;

    public List<CarsByMakeSingleDto> getCars() {
        return cars;
    }

    public void setCars(List<CarsByMakeSingleDto> cars) {
        this.cars = cars;
    }
}
