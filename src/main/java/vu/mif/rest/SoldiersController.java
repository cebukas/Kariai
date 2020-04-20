package vu.mif.rest;

import lombok.Getter;
import lombok.Setter;
import vu.mif.entities.Soldier;
import vu.mif.persistence.SoldiersDAO;
import vu.mif.rest.contracts.SoldierDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/soldiers")
public class SoldiersController {
    @Inject
    @Setter @Getter
    private SoldiersDAO soldiersDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Long id) {
        Soldier soldier = soldiersDAO.findOne(id);
        if (soldier == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        SoldierDTO soldierDTO = new SoldierDTO();
        soldierDTO.setName(soldier.getName());
        soldierDTO.setUndercoverName(soldier.getUndercoverName());
        soldierDTO.setArmyUnit(soldier.getArmyUnit().getName());

        return Response.ok(soldierDTO).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Long soldierId,
            SoldierDTO soldierData) {
        try {
            Soldier existingSoldier = soldiersDAO.findOne(soldierId);
            if (existingSoldier == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingSoldier.setName(soldierData.getName());
            existingSoldier.setUndercoverName(soldierData.getUndercoverName());
            soldiersDAO.update(existingSoldier);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
