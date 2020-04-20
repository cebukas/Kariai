package vu.mif.persistence;

import vu.mif.entities.ArmyUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ArmyUnitsDAO {
    @Inject
    private EntityManager entityManager;

    public List<ArmyUnit> loadAll() {
        return entityManager.createNamedQuery("ArmyUnit.findAll", ArmyUnit.class).getResultList();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void persist(ArmyUnit armyUnit){
        this.entityManager.persist(armyUnit);
    }

    public ArmyUnit findOne(Long id) { return entityManager.find(ArmyUnit.class, id); }
}
