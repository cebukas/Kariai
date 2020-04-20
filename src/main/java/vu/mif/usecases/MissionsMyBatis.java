package vu.mif.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.mif.myBatis.DAO.ArmyunitMapper;
import vu.mif.myBatis.DAO.ArmyunitMissionMapper;
import vu.mif.myBatis.DAO.MissionMapper;
import vu.mif.myBatis.DAO.SoldierMapper;
import vu.mif.myBatis.model.Armyunit;
import vu.mif.myBatis.model.ArmyunitMission;
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

    @Inject
    private ArmyunitMissionMapper armyunitMissionMapper;

    @Getter @Setter
    private List<Mission> missions;

    @Getter @Setter
    private Mission mission;

    @Getter @Setter
    private Armyunit armyunit;

    @Getter @Setter
    private ArmyunitMission armyunitMissionToCreate = new ArmyunitMission();

    @Getter @Setter
    private Mission missionToCreate = new Mission();


    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long armyUnitId = Long.parseLong(requestParameters.get("armyUnitId"));

        this.armyunit =  unitMapper.selectByPrimaryKey(armyUnitId);
        this.missions = missionMapper.selectByUnit(armyUnitId);

    }

    @Transactional
    public String createMission() {
        try{
            missionMapper.insert(missionToCreate);

            armyunitMissionToCreate.setArmyunitsId(this.armyunit.getId());
            armyunitMissionToCreate.setMissionsMissionid(missionMapper.selectLast().getMissionid());
           armyunitMissionMapper.insert(armyunitMissionToCreate);

        }
        catch (Exception e){
            return "/missions?faces-redirect=true&armyUnitId=" + this.armyunit.getId() + "&faces-redirect=true";

        }
        return "/missions?faces-redirect=true&armyUnitId="+ this.armyunit.getId()+ "&faces-redirect=true";
    }
}
