import entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "visitations")
public class Visitation  extends BaseEntity {
    private LocalDate date;
    private String comments;
    private Patient patient;
    private Diagnose diagnosis;

    public Visitation() {

    }

    @ManyToOne
    public Diagnose getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnose diagnosis) {
        this.diagnosis = diagnosis;
    }

    @ManyToOne
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Column(columnDefinition = "TEXT")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
