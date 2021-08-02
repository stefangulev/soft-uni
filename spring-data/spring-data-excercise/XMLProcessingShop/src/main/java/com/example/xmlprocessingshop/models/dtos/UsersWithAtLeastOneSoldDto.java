package com.example.xmlprocessingshop.models.dtos;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersWithAtLeastOneSoldDto {
    @XmlElement(name = "user")
    List<UsersWithAtLeastOneSoldSingleDto> users;

    public UsersWithAtLeastOneSoldDto() {

    }

    public List<UsersWithAtLeastOneSoldSingleDto> getUsers() {
        return users;
    }

    public void setUsers(List<UsersWithAtLeastOneSoldSingleDto> users) {
        this.users = users;
    }
}
