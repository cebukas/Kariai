package vu.mif.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.mif.entities.ArmyUnit;
import vu.mif.entities.Soldier;
import vu.mif.interceptors.LoggedInvocation;
import vu.mif.persistence.ArmyUnitsDAO;
import vu.mif.persistence.SoldiersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class SoldiersForArmyUnit implements Serializable {
    @Inject
    private ArmyUnitsDAO armyUnitsDAO;

    @Inject
    private SoldiersDAO soldiersDAO;

    @Getter @Setter
    private ArmyUnit armyUnit;

    @Getter @Setter
    private Soldier soldierToCreate = new Soldier();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long armyUnitId = Long.parseLong(requestParameters.get("armyUnitId"));
        this.armyUnit = armyUnitsDAO.findOne(armyUnitId);
    }
    @Transactional
    @LoggedInvocation
    public String createSoldier(){
        try{
            soldierToCreate.setArmyUnit(this.armyUnit);
            soldiersDAO.persist(soldierToCreate);
            return "/soldiers?faces-redirect=true&armyUnitId=" + this.armyUnit.getId();
        }
        catch (Exception e){
            return "/soldiers?faces-redirect=true&armyUnitId=" + this.armyUnit.getId();
        }
    }
}
