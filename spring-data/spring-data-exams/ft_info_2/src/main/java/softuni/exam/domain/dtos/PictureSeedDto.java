package softuni.exam.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "pictures")
@XmlAccessorType(XmlAccessType.FIELD)
public class PictureSeedDto {
    @XmlElement(name = "picture")
    private List<PictureSeedSingleDto> pictures;

    public List<PictureSeedSingleDto> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureSeedSingleDto> pictures) {
        this.pictures = pictures;
    }
}
