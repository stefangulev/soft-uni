package bg.softuni.mobilelele.model.binding;

import bg.softuni.mobilelele.model.validator.UniqueUserName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {
    @NotNull
    @Size(min = 4, max = 20)
    private String firstName;
    @NotNull
    @Size(min = 4, max = 20)
    private String lastName;
    @NotNull
    @Size(min = 4, max = 20)
    @UniqueUserName
    private String username;
    @NotBlank
    private String password;
    private String roles;

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

    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRoles() {
        return roles;
    }

    public UserRegisterBindingModel setRoles(String roles) {
        this.roles = roles;
        return this;
    }
}
