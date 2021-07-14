import entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "patient")
public class Patient extends BaseEntity {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private LocalDate birthDate;
    private String picture;
    private boolean hasMedicalInsurance;
    private Set<Visitation> visitationSetSet;

    public Patient() {
        this.visitationSetSet = new LinkedHashSet<>();
    }
    public Patient(String firstName, String lastName) {
        this.visitationSetSet = new LinkedHashSet<>();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @OneToMany(mappedBy = "patient")
    public Set<Visitation> getVisitationSetSet() {
        return visitationSetSet;
    }

    public void setVisitationSetSet(Set<Visitation> visitationSetSet) {
        this.visitationSetSet = visitationSetSet;
    }




    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name = "bith_date", columnDefinition = "BLOB")
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
    @Column(name = "has_medical_insurance")
    public boolean isHasMedicalInsurance() {
        return hasMedicalInsurance;
    }

    public void setHasMedicalInsurance(boolean hasMedicalInsurance) {
        this.hasMedicalInsurance = hasMedicalInsurance;
    }
}
