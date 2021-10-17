package bg.softuni.mobilelele.user;

import bg.softuni.mobilelele.model.entities.UserRoleEntity;
import bg.softuni.mobilelele.model.entities.UserRoleEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@SessionScope
public class CurrentUser {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private boolean isLoggedIn;
    private Set<UserRoleEntity> roles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public CurrentUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CurrentUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CurrentUser setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CurrentUser setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public CurrentUser setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
        return this;
    }

    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public CurrentUser setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }
    public CurrentUser addRole(UserRoleEntity userRoleEntity){
        this.roles.add(userRoleEntity);
        return this;
    }
    public CurrentUser clearRoler() {
        this.roles.clear();
        return this;
    }
    public boolean isAdmin() {
       return this.roles.stream().filter(r -> r.getRole() == UserRoleEnum.ADMIN).collect(Collectors.toSet()).size() > 0;
    }

    public void clean() {
        setLoggedIn(false).setUsername(null).setFirstName(null).setLastName(null);
    }
}
