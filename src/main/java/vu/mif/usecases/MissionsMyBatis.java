package vu.mif.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.mif.myBatis.DAO.ArmyunitMapper;
import vu.mif.myBatis.DAO.MissionMapper;
import vu.mif.myBatis.DAO.SoldierMapper;
import vu.mif.myBatis.model.Armyunit;
import vu.mif.myBatis.model.Mission;
import vu.mif.myBatis.model.Soldier;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class MissionsMyBatis {
    @Inject
    private ArmyunitMapper unitMapper;

    @Inject
    private MissionMapper missionMapper;

    @Getter @Setter
    private List<Mission> missions;

    @Getter @Setter
    private List<Armyunit> armyunits;

    @Getter @Setter
    private Mission missionToCreate = new Mission();


    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long armyUnitId = Long.parseLong(requestParameters.get("armyunitId"));
        this.armyunits = (List<Armyunit>) unitMapper.selectByPrimaryKey(armyUnitId);
      //  this.missions = missionMapper.selectByUnit(armyunits);
    }

    @Transactional
    public String createSoldier() {
        try{
         //   soldierToCreate.setArmyunitId(this.armyunit.getId());
           // soldierMapper.insert(soldierToCreate);

        }
        catch (Exception e){
            //return "/MyBatis/soldiers?faces-redirect=true&armyunitId=" + this.armyunit.getId() + "&faces-redirect=true";

        }
        return "/MyBatis/soldiers?faces-redirect=true&armyunitId=" + "&faces-redirect=true";
    }
}
