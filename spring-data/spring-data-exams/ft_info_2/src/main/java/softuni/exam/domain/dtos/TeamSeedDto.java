package softuni.exam.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "teams")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamSeedDto {
    @XmlElement(name = "team")
    private List<TeamSeedSingleDto> teams;

    public List<TeamSeedSingleDto> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamSeedSingleDto> teams) {
        this.teams = teams;
    }
}
