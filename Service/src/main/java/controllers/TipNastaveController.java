/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.StudijskiProgram;
import domain.TipNastave;
import dto.StudijskiProgramDTO;
import dto.TipNastaveDTO;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import logic.TipNastaveLogic;
import mapper.Mapper;

/**
 *
 * @author Petar
 */
@Path("tip-nastave")
public class TipNastaveController {

    private final TipNastaveLogic tnl;

    public TipNastaveController() {
        this.tnl = new TipNastaveLogic();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response getAll() {
        try {
            List<TipNastave> tipoviNastave = tnl.getAll();

            if (tipoviNastave == null || tipoviNastave.isEmpty()) {
                return Response.noContent().build();
            }

            List<TipNastaveDTO> tipoviNastaveDTO = new ArrayList<>();

            for (TipNastave tn : tipoviNastave) {
                tipoviNastaveDTO.add(Mapper.toTipNastaveDTO(tn));
            }

            return Response.ok(tipoviNastaveDTO).build();
        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response getById(@PathParam("id") @NotNull int id) {
        try {
            TipNastave tipNastave = tnl.getById(id);

            if (tipNastave == null) {
                return Response.noContent().build();
            }

            return Response.ok(Mapper.toTipNastaveDTO(tipNastave)).build();

        } catch (Exception e) {
            return Response.serverError().type(MediaType.APPLICATION_JSON).build();
        }
    }

}
