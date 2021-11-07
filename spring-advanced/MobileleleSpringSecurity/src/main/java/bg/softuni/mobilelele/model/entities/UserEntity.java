package bg.softuni.mobilelele.model.entities;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column(name = "is_active",  nullable = false)
    private boolean isActive;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRoleEntity> roles;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(nullable = false)
    private Instant created;
    private Instant modified;
    @OneToMany(mappedBy = "seller")
    private Set<OfferEntity> offers;
    public UserEntity() {
        this.roles = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public UserEntity setActive(boolean active) {
        isActive = active;
        return this;
    }

    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public UserEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public UserEntity setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public Instant getModified() {
        return modified;
    }

    public UserEntity setModified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public Set<OfferEntity> getOffers() {
        return offers;
    }

    public UserEntity setOffers(Set<OfferEntity> offers) {
        this.offers = offers;
        return this;
    }

    @PrePersist
    public void setCreatedDateAndActiveness() {
        this.created = Instant.now();
        this.isActive = true;
    }
}
