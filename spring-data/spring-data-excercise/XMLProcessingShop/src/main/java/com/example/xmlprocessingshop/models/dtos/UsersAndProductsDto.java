package com.example.xmlprocessingshop.models.dtos;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersAndProductsDto {
    @XmlElement(name = "user")
    List<UserAndProductsSingleDto> users;
    @XmlAttribute(name = "count")
    private Long count;

    public UsersAndProductsDto() {

    }

    public List<UserAndProductsSingleDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserAndProductsSingleDto> users) {
        this.users = users;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
