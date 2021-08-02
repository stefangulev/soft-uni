package softuni.exam.instagraphlite.models.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "post")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostSeedSingleDto {
    @XmlElement(name = "caption")
    private String caption;
    @XmlElement(name = "user")
    private PostUserDto user;
    @XmlElement(name = "picture")
    private PostPictureDto picture;

    @Size(min = 21)
    @NotBlank
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public PostUserDto getUser() {
        return user;
    }

    public void setUser(PostUserDto user) {
        this.user = user;
    }

    public PostPictureDto getPicture() {
        return picture;
    }

    public void setPicture(PostPictureDto picture) {
        this.picture = picture;
    }
}
