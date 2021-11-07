package bg.softuni.mobilelele.model.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRoleEnum role;
    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users;


    public UserRoleEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public UserRoleEntity setUsers(Set<UserEntity> users) {
        this.users = users;
        return this;
    }
}
