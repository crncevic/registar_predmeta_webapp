/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.Uloga;
import dto.UlogaDTO;
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
import logic.UlogaLogic;
import mapper.Mapper;

/**
 *
 * @author Petar
 */
@Path("uloga")
public class UlogaController {

    @Inject
    private UlogaLogic ul;

    

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response getAll() {
        try {
            List<Uloga> uloge = ul.getAll();

            if (uloge == null || uloge.isEmpty()) {
                return Response.noContent().build();
            }

            List<UlogaDTO> ulogeDTO = new ArrayList<>();

            for (Uloga u : uloge) {
                ulogeDTO.add(Mapper.toUlogaDTO(u));
            }

            return Response.ok(ulogeDTO).build();
        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response getById(@PathParam("id") @NotNull int id) {
        try {
            Uloga uloga = ul.getById(id);

            if (uloga == null) {
                return Response.noContent().build();
            }

            return Response.ok(Mapper.toUlogaDTO(uloga)).build();

        } catch (Exception e) {
            return Response.serverError().type(MediaType.APPLICATION_JSON).build();
        }
    }

}
