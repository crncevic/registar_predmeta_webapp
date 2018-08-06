/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.Katedra;
import dto.KatedraDTO;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import logic.KatedraLogic;

/**
 *
 * @author Petar
 */
@Path("katedra")
public class KatedraController {

    private KatedraLogic kl;

    public KatedraController() {
        kl = new KatedraLogic();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        try {
            List<KatedraDTO> katedreDTO = kl.getAll();

            if (katedreDTO == null || katedreDTO.isEmpty()) {
                return Response.noContent().build();
            }

            return Response.ok(katedreDTO).build();

        } catch (Exception ex) {
            return Response.serverError().build();
        }

    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") @NotNull int id) {
        try {
            KatedraDTO katedraDTO = kl.getById(id);

            if (katedraDTO == null) {
                return Response.noContent().build();
            }

            return Response.ok(katedraDTO).build();

        } catch (Exception e) {
            return Response.serverError().entity(e).build();
        }
    }

}
