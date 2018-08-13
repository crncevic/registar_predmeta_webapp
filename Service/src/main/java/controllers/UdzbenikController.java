/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.TipNastave;
import domain.Udzbenik;
import dto.TipNastaveDTO;
import dto.UdzbenikDTO;
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
import logic.UdzbenikLogic;
import mapper.Mapper;

/**
 *
 * @author Petar
 */
@Path("udzbenik")
public class UdzbenikController {

    @Inject
    private UdzbenikLogic ul;
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response getAll() {
        try {
            List<Udzbenik> udzbenici = ul.getAll();

            if (udzbenici == null || udzbenici.isEmpty()) {
                return Response.noContent().build();
            }

            List<UdzbenikDTO> udzbeniciDTO = new ArrayList<>();

            for (Udzbenik u : udzbenici) {
                udzbeniciDTO.add(Mapper.toUdzbenikDTO(u));
            }

            return Response.ok(udzbeniciDTO).build();
        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response getById(@PathParam("id") @NotNull int id) {
        try {
            Udzbenik udzbenik = ul.getById(id);

            if (udzbenik == null) {
                return Response.noContent().build();
            }

            return Response.ok(Mapper.toUdzbenikDTO(udzbenik)).build();

        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response create(@NotNull UdzbenikDTO udzbenikDTO) {
        try {
            Udzbenik udzbenik = Mapper.toUdzbenik(udzbenikDTO);
            UdzbenikDTO createdUdzbenikDTO = Mapper.toUdzbenikDTO(ul.create(udzbenik));
            return Response.ok(createdUdzbenikDTO).build();
        } catch (ConstraintViolationException cve) {
            return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(cve).build();
        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response update(@NotNull UdzbenikDTO udzbenikDTO, @PathParam("id") @NotNull int id) {
        try {
            if (id != udzbenikDTO.getUdzbenikId()) {
                return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity("Id iz URL putanje i HTTP body-a se ne slazu!").build();
            }

            Udzbenik udzbenik = Mapper.toUdzbenik(udzbenikDTO);
            UdzbenikDTO updatedUdzbenikDTO = Mapper.toUdzbenikDTO(ul.update(udzbenik));

            return Response.ok(updatedUdzbenikDTO).build();

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
            UdzbenikDTO deletedUdzbenikDTO = Mapper.toUdzbenikDTO(ul.delete(id));
            return Response.ok(deletedUdzbenikDTO).build();
        } catch (ConstraintViolationException cve) {
            return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(cve).build();
        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

}
