package softuni.exam.models;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sellers")
public class Seller extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private Rating rating;
    private String town;
    private Set<Offer> offers;

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email", unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "rating", nullable = false)
    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Column(name = "town", nullable = false)
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @OneToMany(mappedBy = "seller")
    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }
}
