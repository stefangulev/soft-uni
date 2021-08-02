package softuni.exam.domain.dtos;

import softuni.exam.domain.entities.Picture;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "team")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamSeedSingleDto {
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "picture")
    private TeamPictureDto picture;

    @Size(min = 3, max = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeamPictureDto getPicture() {
        return picture;
    }

    public void setPicture(TeamPictureDto picture) {
        this.picture = picture;
    }
}
