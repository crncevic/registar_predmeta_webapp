/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.TematskaCelina;
import dto.TematskaCelinaDTO;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import logic.TematskaCelinaLogic;
import mapper.Mapper;

/**
 *
 * @author Petar
 */
@Path("tematska-celina")
public class TematskaCelinaController {
 
    @Inject
    private TematskaCelinaLogic tcl;

    @GET
    @Path("predmet/{predmetId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response getByPredmetId(@PathParam("predmetId") @NotNull int predmetId) {
        try {
            List<TematskaCelina> tematskeCeline = tcl.getByPredmetId(predmetId);

            if (tematskeCeline == null || tematskeCeline.isEmpty()) {
                return Response.noContent().build();
            }

            List<TematskaCelinaDTO> tematskeCelineDTO = new ArrayList<>();

            for (TematskaCelina tematskaCelina : tematskeCeline) {
                tematskeCelineDTO.add(Mapper.toTematskaCelinaDTO(tematskaCelina));
            }

            return Response.ok(tematskeCelineDTO).build();
        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }

    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("{id}") @NotNull int id) {
        try {
            TematskaCelina tematskaCelina = tcl.getById(id);

            if (tematskaCelina == null) {
                return Response.noContent().build();
            }

            return Response.ok(Mapper.toTematskaCelinaDTO(tematskaCelina)).build();

        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

}
