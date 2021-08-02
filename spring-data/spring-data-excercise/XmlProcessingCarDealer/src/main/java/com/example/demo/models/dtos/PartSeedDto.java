package com.example.demo.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartSeedDto {
    @XmlElement(name = "part")
    List<PartSeedSingleDto> parts;

    public List<PartSeedSingleDto> getParts() {
        return parts;
    }

    public void setParts(List<PartSeedSingleDto> parts) {
        this.parts = parts;
    }
}
