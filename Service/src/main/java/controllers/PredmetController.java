/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.Predmet;
import dto.PredmetDTO;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import logic.PredmetLogic;
import mapper.Mapper;

/**
 *
 * @author Petar
 */
@Path("predmet")

public class PredmetController {

    @Inject
    private PredmetLogic pl;

    

    @GET
    @Produces({MediaType.APPLICATION_JSON})

    public Response getAll() {
        try {

            List<Predmet> predmeti = pl.getAll();

            if (predmeti == null || predmeti.isEmpty()) {
                return Response.noContent().build();
            }

            List<PredmetDTO> predmetiDTO = new ArrayList<>();

            for (Predmet predmet : predmeti) {
                predmetiDTO.add(Mapper.toPredmetDTO(predmet));
            }

            return Response.ok(predmetiDTO).build();

        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response getById(@PathParam("id") @NotNull int id) {
        try {

            Predmet predmet = pl.getById(id);

            if (predmet == null) {
                return Response.noContent().build();
            }

            return Response.ok(Mapper.toPredmetDTO(predmet)).build();

        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response create(@NotNull PredmetDTO predmetDTO) {
        try {
            Predmet predmet = Mapper.toPredmet(predmetDTO);
            PredmetDTO createdPredmet = Mapper.toPredmetDTO(pl.create(predmet));
            return Response.ok(createdPredmet).build();
        } catch (ConstraintViolationException cve) {
            return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(cve).build();
        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response update(@PathParam("id") @NotNull int id, @NotNull PredmetDTO predmetDTO) {
        try {
            Predmet predmetForUpdate = Mapper.toPredmet(predmetDTO);
            PredmetDTO updatedPredmetDTO = Mapper.toPredmetDTO(pl.update(predmetForUpdate));

            return Response.ok(updatedPredmetDTO).build();

        } catch (ConstraintViolationException cve) {
            return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(cve).build();
        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response delete(@PathParam("id") @NotNull int id) {
        try {
            PredmetDTO deletedPredmetDTO = Mapper.toPredmetDTO(pl.delete(id));
            return Response.ok(deletedPredmetDTO).build();
        } catch (ConstraintViolationException cve) {
            return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(cve).build();
        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

}
