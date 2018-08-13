/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.UlogaUdzbenik;
import dto.UlogaUdzbenikDTO;
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
import logic.UlogaUdzbenikLogic;
import mapper.Mapper;

/**
 *
 * @author Petar
 */
@Path("uloga-udzbenik")
public class UlogaUdzbenikController {

    @Inject
    private UlogaUdzbenikLogic uul;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response getAll() {
        try {
            List<UlogaUdzbenik> ulogeUdzbenik = uul.getAll();

            if (ulogeUdzbenik == null || ulogeUdzbenik.isEmpty()) {
                return Response.noContent().build();
            }

            List<UlogaUdzbenikDTO> ulogeUdzbenikDTO = new ArrayList<>();

            for (UlogaUdzbenik u : ulogeUdzbenik) {
                ulogeUdzbenikDTO.add(Mapper.toUlogaUdzbenikDTO(u));
            }

            return Response.ok(ulogeUdzbenikDTO).build();
        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response getById(@PathParam("id") @NotNull int id) {
        try {
            UlogaUdzbenik ulogaUdzbenik = uul.getById(id);

            if (ulogaUdzbenik == null) {
                return Response.noContent().build();
            }

            return Response.ok(Mapper.toUlogaUdzbenikDTO(ulogaUdzbenik)).build();

        } catch (Exception e) {
            return Response.serverError().type(MediaType.APPLICATION_JSON).build();
        }
    }

}
