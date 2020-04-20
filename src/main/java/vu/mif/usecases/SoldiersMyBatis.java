package vu.mif.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.mif.myBatis.DAO.ArmyunitMapper;
import vu.mif.myBatis.DAO.SoldierMapper;
import vu.mif.myBatis.model.Armyunit;
import vu.mif.myBatis.model.Soldier;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class SoldiersMyBatis {
    @Inject
    private ArmyunitMapper unitMapper;

    @Inject
    private SoldierMapper soldierMapper;

    @Getter @Setter
    private List<Soldier> allSoldiers;

    @Getter @Setter
    private Armyunit armyunit;

    @Getter @Setter
    private Soldier soldierToCreate = new Soldier();


    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long armyUnitId = Long.parseLong(requestParameters.get("armyunitId"));
        this.armyunit = unitMapper.selectByPrimaryKey(armyUnitId);
        this.allSoldiers = soldierMapper.selectByUnit(armyUnitId);
    }

    @Transactional
    public String createSoldier() {
        try{
            soldierToCreate.setArmyunitId(this.armyunit.getId());
            soldierMapper.insert(soldierToCreate);

        }
        catch (Exception e){
            return "/MyBatis/soldiers?faces-redirect=true&armyunitId=" + this.armyunit.getId() + "&faces-redirect=true";

        }
        return "/MyBatis/soldiers?faces-redirect=true&armyunitId=" + this.armyunit.getId() + "&faces-redirect=true";
    }
}
