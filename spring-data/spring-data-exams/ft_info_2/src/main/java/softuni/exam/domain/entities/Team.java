package softuni.exam.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity {
    private String name;
    private Picture picture;
    private Set<Player> player;

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(optional = false)
    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    @OneToMany(mappedBy = "team")
    public Set<Player> getPlayer() {
        return player;
    }

    public void setPlayer(Set<Player> player) {
        this.player = player;
    }
}
