package vu.mif.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "ArmyUnit.findAll", query = "select a from ArmyUnit as a")
})
@Table(name = "ARMYUNIT")
@Getter @Setter
public class ArmyUnit {

    public ArmyUnit(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME", unique=true)
    private String name;

    @OneToMany(mappedBy = "armyUnit")
    private List<Soldier> soldiers = new ArrayList<>();

    @ManyToMany
    @JoinColumn(name="MISSIONID")
    private List<Mission> missions = new ArrayList<Mission>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArmyUnit armyUnit = (ArmyUnit) o;
        return Objects.equals(id, armyUnit.id) &&
                Objects.equals(name, armyUnit.name);
    }

    @Override
    public int hashCode() { return Objects.hash(name); }
}