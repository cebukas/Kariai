package vu.mif.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.mif.myBatis.DAO.ArmyunitMapper;
import vu.mif.myBatis.model.Armyunit;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class UnitsMyBatis {
    @Inject
    private ArmyunitMapper unitMapper;

    @Getter
    private List<Armyunit> allUnits;

    @Getter @Setter
    private Armyunit unitToCreate = new Armyunit();

    @PostConstruct
    public void init() {
        this.loadAllUnits();
    }

    private void loadAllUnits() {
        this.allUnits = unitMapper.selectAll();
    }


    @Transactional
    public String createUnit() {
        try{
            unitMapper.insert(unitToCreate);
        }
        catch (Exception e){
            return "/MyBatis/units?faces-redirect=true";
        }
        return "/MyBatis/units?faces-redirect=true";
    }
}