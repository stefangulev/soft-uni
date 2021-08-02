package softuni.exam.domain.dtos;

import com.google.gson.annotations.Expose;
import softuni.exam.domain.entities.Position;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class PlayerSeedDto {
    @Expose
    @NotBlank
    private String firstName;
    @Expose
    @Size(min = 3, max = 15)
    private String lastName;
    @Expose
    @Min(1)
    @Max(99)
    private Integer number;
    @Expose
    @NotNull
    @DecimalMin(value = "0")
    private BigDecimal salary;
    @Expose
    @NotNull
    private Position position;
    @Expose
    private PlayerPictureDto picture;
    @Expose
    private PlayerTeamDto team;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public PlayerPictureDto getPicture() {
        return picture;
    }

    public void setPicture(PlayerPictureDto picture) {
        this.picture = picture;
    }

    public PlayerTeamDto getTeam() {
        return team;
    }

    public void setTeam(PlayerTeamDto team) {
        this.team = team;
    }
}
