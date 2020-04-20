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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Model
public class AllMissions {

    @Inject
    private ArmyunitMissionMapper armyunitMissionMapper;


    @Getter @Setter
    private List<ArmyunitMission> armyunitMissionList = new ArrayList<>();
    @PostConstruct
        public void init() {
            this.loadAllUnits();
        }

        private void loadAllUnits() {
            this.armyunitMissionList = armyunitMissionMapper.selectAll();
        }
}
