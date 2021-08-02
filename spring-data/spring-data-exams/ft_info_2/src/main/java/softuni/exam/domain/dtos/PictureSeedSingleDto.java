package softuni.exam.domain.dtos;

import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "picture")
@XmlAccessorType(XmlAccessType.FIELD)
public class PictureSeedSingleDto {
    @XmlElement(name = "url")
    private String url;

    @NotBlank
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
