package vu.mif.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Mission.findAll", query = "select m from Mission as m")
})
@Table(name = "MISSION")
@Getter @Setter
public class Mission {
    public Mission(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long missionId;
    @Column(unique=true)
    private String name;
    private String country;

    @ManyToMany(mappedBy = "missions")
    private List<ArmyUnit> armyUnits = new ArrayList<ArmyUnit>();

}
