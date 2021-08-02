package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "stats")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatSeedDto {
    @XmlElement(name = "stat")
    private List<StatSingleDto> stats;

    public List<StatSingleDto> getStats() {
        return stats;
    }

    public void setStats(List<StatSingleDto> stats) {
        this.stats = stats;
    }
}
