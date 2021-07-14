import entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "medicaments")
public class Medicament extends BaseEntity {
    private String name;
    private Set<Diagnose> diagnoseSet;

    public Medicament() {
        this.diagnoseSet = new LinkedHashSet<>();
    }

    @ManyToMany(mappedBy = "medicamentSet")
    public Set<Diagnose> getDiagnoseSet() {
        return diagnoseSet;
    }

    public void setDiagnoseSet(Set<Diagnose> diagnoseSet) {
        this.diagnoseSet = diagnoseSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
