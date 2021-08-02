package softuni.exam.domain.dtos;

import com.google.gson.annotations.Expose;

public class PlayerTeamDto {
    @Expose
    private String name;
    @Expose
    private PlayerTeamPictureDto picture;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerTeamPictureDto getPicture() {
        return picture;
    }

    public void setPicture(PlayerTeamPictureDto picture) {
        this.picture = picture;
    }
}
