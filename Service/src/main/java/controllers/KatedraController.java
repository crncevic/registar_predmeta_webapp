/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.Katedra;
import dto.KatedraDTO;
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
import logic.KatedraLogic;
import mapper.Mapper;

/**
 *
 * @author Petar
 */
@Path("katedra")
public class KatedraController {

    @Inject
    private KatedraLogic kl;


    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public Response getAll() {
        try {
            List<Katedra> katedre = kl.getAll();

            if (katedre == null || katedre.isEmpty()) {
                return Response.noContent().build();
            }

            List<KatedraDTO> katedreDTO = new ArrayList<>();

            for (Katedra katedra : katedre) {
                katedreDTO.add(Mapper.toKatedraDTO(katedra));
            }

            return Response.ok(katedreDTO).build();

        } catch (Exception ex) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(ex).build();
        }

    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public Response getById(@PathParam("id") @NotNull int id) {
        try {
            Katedra katedra = kl.getById(id);

            if (katedra == null) {
                return Response.noContent().build();
            }

            return Response.ok(Mapper.toKatedraDTO(katedra)).build();

        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

}
