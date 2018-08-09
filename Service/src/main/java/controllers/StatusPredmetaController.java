/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.Status;
import dto.StatusDTO;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import logic.StatusLogic;
import mapper.Mapper;

/**
 *
 * @author Petar
 */
@Path("status")
public class StatusPredmetaController {

    private final StatusLogic sl;

    public StatusPredmetaController() {
        sl = new StatusLogic();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response getAll() {
        try {
            List<Status> statusi = sl.getAll();

            if (statusi == null || statusi.isEmpty()) {
                return Response.noContent().build();
            }

            List<StatusDTO> statusiDTO = new ArrayList<>();

            for (Status status : statusi) {
                statusiDTO.add(Mapper.toStatusDTO(status));
            }

            return Response.ok(statusiDTO).build();
        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response getById(@PathParam("id") @NotNull int id) {
        try {
            Status status = sl.getById(id);

            if (status == null) {
                return Response.noContent().build();
            }

            return Response.ok(Mapper.toStatusDTO(status)).build();

        } catch (Exception e) {
            return Response.serverError().type(MediaType.APPLICATION_JSON).build();
        }
    }
}
