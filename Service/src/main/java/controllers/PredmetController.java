/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import domain.Predmet;
import dto.PredmetDTO;
import java.util.List;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

    private final PredmetLogic pl;

    public PredmetController() {
        pl = new PredmetLogic();
           
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    
    public Response getAll() {
        try {
           
            List<PredmetDTO> predmetiDTO = pl.getAll();
         
            if (predmetiDTO == null || predmetiDTO.isEmpty()) {
                return Response.noContent().build();
            }

            return Response.ok(predmetiDTO).build();

        } catch (Exception e) {
            return Response.serverError().entity(e).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") @NotNull int id) {
        try {
            
           
            PredmetDTO predmetDTO = pl.getById(id);

            if (predmetDTO == null) {
                return Response.noContent().build();
            }

            return Response.ok(predmetDTO).build();

        } catch (Exception e) {
            return Response.serverError().entity(e).build();
        }
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(@NotNull PredmetDTO predmetDTO) {
        try {
            Predmet predmet = Mapper.toPredmet(predmetDTO);
            Predmet createdPredmet = pl.create(predmet);
            return Response.ok(createdPredmet).build();
        } catch (ConstraintViolationException cve) {
            return Response.status(Response.Status.BAD_REQUEST).entity(cve).build();
        } catch (Exception e) {
            return Response.serverError().entity(e).build();
        }
    }

}
