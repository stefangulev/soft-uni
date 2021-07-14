package entity;

import javax.persistence.*;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "people")
public abstract class BasePerson extends BaseEntity {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public BasePerson() {

    }

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

    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
