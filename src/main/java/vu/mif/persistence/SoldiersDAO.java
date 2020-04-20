package vu.mif.persistence;

import vu.mif.entities.ArmyUnit;
import vu.mif.entities.Soldier;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class SoldiersDAO {
    @Inject
    private EntityManager entityManager;

    public void persist(Soldier soldier){
        this.entityManager.persist(soldier);
    }

    public Soldier findOne(Long id){
        return entityManager.find(Soldier.class, id);
    }

    public Soldier update(Soldier soldier){
        return entityManager.merge(soldier);
    }

    public List<Soldier> loadAll() {
        return entityManager.createNamedQuery("Soldier.findAll", Soldier.class).getResultList();
    }
}
