/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.PredmetNaStudijskomProgramu;
import domain.Status;
import dto.PredmetNaStudijskomProgramuDTO;
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
import logic.PredmetNaStudijskomProgramuLogic;
import mapper.Mapper;

/**
 *
 * @author Petar
 */
@Path("predmet-na-std-programu")
public class PredmetNaStdProgramuController {

    @Inject
    PredmetNaStudijskomProgramuLogic pnspl;

    @GET
    @Path("/{stdProgramId}/{predmetId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response getById(@PathParam("stdProgramId") @NotNull int stdProgramId, @PathParam("predmetId") @NotNull int predmetId) {
        try {
            PredmetNaStudijskomProgramu pnsp = pnspl.getById(stdProgramId, predmetId);

            if (pnsp == null) {
                return Response.noContent().build();
            }

            return Response.ok(Mapper.toPredmetNaStudijskomProgramuDTO(pnsp)).build();

        } catch (Exception e) {
            return Response.serverError().type(MediaType.APPLICATION_JSON).build();
        }
    }

    @GET
    @Path("/{stdProgramId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response getByStdProgramId(@PathParam("stdProgramId") @NotNull int stdProgramId) {
        try {
            List<PredmetNaStudijskomProgramu> predmetiNaStdProgramu = pnspl.getByStdProgramId(stdProgramId);

            if (predmetiNaStdProgramu == null || predmetiNaStdProgramu.isEmpty()) {
                return Response.noContent().build();
            }

            List<PredmetNaStudijskomProgramuDTO> pnspdtos = new ArrayList<>();

            for (PredmetNaStudijskomProgramu pnsp : predmetiNaStdProgramu) {
                pnspdtos.add(Mapper.toPredmetNaStudijskomProgramuDTO(pnsp));
            }

            return Response.ok(pnspdtos).build();

        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response create(@NotNull PredmetNaStudijskomProgramuDTO pnspdto) {
        try {

            PredmetNaStudijskomProgramu predmetNaStudijskomProgramu = Mapper.toPredmetNaStudjskomProgramu(pnspdto);
            PredmetNaStudijskomProgramuDTO createdPnspDTO = Mapper.toPredmetNaStudijskomProgramuDTO(pnspl.create(predmetNaStudijskomProgramu));

            return Response.ok(createdPnspDTO).build();

        } catch (ConstraintViolationException cve) {
            return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(cve).build();
        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response update(@NotNull PredmetNaStudijskomProgramuDTO pnspdto) {
        try {

            PredmetNaStudijskomProgramu predmetNaStudijskomProgramu = Mapper.toPredmetNaStudjskomProgramu(pnspdto);
            PredmetNaStudijskomProgramuDTO updatedPnspDTO = Mapper.toPredmetNaStudijskomProgramuDTO(pnspl.update(predmetNaStudijskomProgramu));

            return Response.ok(updatedPnspDTO).build();

        } catch (ConstraintViolationException cve) {
            return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(cve).build();
        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

    @DELETE
    @Path("/{stdProgramId}/{predmetId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response delete(@NotNull @PathParam("stdProgramId") int stdProgramId, @NotNull @PathParam("predmetId") int predmetId) {
        try {

            PredmetNaStudijskomProgramuDTO pnspDTO = Mapper.toPredmetNaStudijskomProgramuDTO(pnspl.delete(stdProgramId, predmetId));
            return Response.ok(pnspDTO).build();

        } catch (ConstraintViolationException cve) {
            return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(cve).build();
        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

}
