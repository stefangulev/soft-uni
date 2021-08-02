package com.example.xmlprocessingshop.models.dtos;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSeedDto {

    @XmlElement(name = "user")
    List<SingleUserSeedDto> users;

    public UserSeedDto() {

    }

    public List<SingleUserSeedDto> getUsers() {
        return users;
    }

    public void setUsers(List<SingleUserSeedDto> users) {
        this.users = users;
    }
}
