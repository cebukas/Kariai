package vu.mif.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.mif.entities.ArmyUnit;
import vu.mif.persistence.ArmyUnitsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class ArmyUnits implements Serializable {
    @Inject
    private ArmyUnitsDAO armyUnitsDAO;

    @Getter @Setter
    private ArmyUnit armyUnitToCreate = new ArmyUnit();

    @Getter
    private List<ArmyUnit> allArmyUnits;

    @PostConstruct
    public void init(){
        loadAllArmyUnits();
    }

    @Transactional
    public String createArmyUnit(){
        try{
            this.armyUnitsDAO.persist(armyUnitToCreate);
        }
        catch (Exception ex){
            return "index?faces-redirect=true";
        }
        return "index?faces-redirect=true";
    }

    private void loadAllArmyUnits(){
        this.allArmyUnits = armyUnitsDAO.loadAll();
    }
}
