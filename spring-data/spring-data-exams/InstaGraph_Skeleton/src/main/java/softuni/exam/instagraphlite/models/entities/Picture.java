package softuni.exam.instagraphlite.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {
    private String path;
    private Double size;

    @Column(nullable = false, unique = true)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Column(nullable = false)
    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }
}
