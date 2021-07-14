import entity.BaseEntity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "diagnoses")
public class Diagnose extends BaseEntity {
    private String name;
    private String comments;
    private Set<Visitation> visitationSet;
    private Set<Medicament> medicamentSet;

    public Diagnose() {
        this.visitationSet = new LinkedHashSet<>();
        this.medicamentSet = new LinkedHashSet<>();
    }


    @ManyToMany
    public Set<Medicament> getMedicamentSet() {
        return medicamentSet;
    }

    public void setMedicamentSet(Set<Medicament> medicamentSet) {
        this.medicamentSet = medicamentSet;
    }

    @OneToMany(mappedBy = "diagnosis")
    public Set<Visitation> getVisitationSet() {
        return visitationSet;
    }

    public void setVisitationSet(Set<Visitation> visitationSet) {
        this.visitationSet = visitationSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(columnDefinition = "TEXT")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
