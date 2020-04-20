package vu.mif.entities;

import lombok.Getter;
import lombok.Setter;

import javax.enterprise.inject.Model;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
@Entity
@NamedQueries({
        @NamedQuery(name = "Soldier.findAll", query = "select a from Soldier as a")
})
@Table(name = "SOLDIER")
@Getter @Setter
public class Soldier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Size(max = 20)
    @Column(name = "NAME", unique=true)
    private String name;
    public Soldier(){

    }

    @ManyToOne
    @JoinColumn(name="ARMYUNIT_ID")
    private ArmyUnit armyUnit;


    @Column(name = "UNDERCOVER_NAME")
    private String undercoverName;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Soldier soldier = (Soldier) o;
        return Objects.equals(id, soldier.id) &&
                Objects.equals(name, soldier.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}
