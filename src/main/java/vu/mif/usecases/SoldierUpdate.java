package vu.mif.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.mif.entities.Soldier;
import vu.mif.interceptors.LoggedInvocation;
import vu.mif.persistence.SoldiersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter @Setter
public class SoldierUpdate implements Serializable {
    private Soldier soldier;

    @Inject
    private SoldiersDAO soldiersDAO;

    @PostConstruct
    private void init() {
        System.out.println("UpdatePlayerDetails INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long soldierId = Long.parseLong(requestParameters.get("soldierId"));
        this.soldier = soldiersDAO.findOne(soldierId);
    }

    @Transactional
    @LoggedInvocation
    public String updateSoldierUndercoverName() {
        try{
            soldiersDAO.update(this.soldier);
        } catch (OptimisticLockException e) {
            return "/soldierDetails.xhtml?faces-redirect=true&soldierId=" + this.soldier.getId() + "&error=optimistic-lock-exception";
        }
        return "/soldierDetails.xhtml?faces-redirect=true&soldierId=" + this.soldier.getId() + "&faces-redirect=true";
    }
}
