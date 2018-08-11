/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.VrstaINivoStudija;
import dto.VrstaINivoStudijaDTO;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import logic.VrstaINivoStudijaLogic;
import mapper.Mapper;

/**
 *
 * @author Petar
 */
@Path("vrsta-i-nivo-studija")
public class VrstaINivoStudijaController {
    
    private final VrstaINivoStudijaLogic vinsl;

    public VrstaINivoStudijaController() {
        this.vinsl = new VrstaINivoStudijaLogic();
    }
    
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response getAll() {
        try {
            List<VrstaINivoStudija> vrsteINivoiStudija = vinsl.getAll();

            if (vrsteINivoiStudija == null || vrsteINivoiStudija.isEmpty()) {
                return Response.noContent().build();
            }

            List<VrstaINivoStudijaDTO> vrsteINivoiStudijaDTO = new ArrayList<>();

            for (VrstaINivoStudija vins : vrsteINivoiStudija ) {
                vrsteINivoiStudijaDTO.add(Mapper.toVrstaINivoStudijaDTO(vins));
            }

            return Response.ok(vrsteINivoiStudijaDTO).build();
        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response getById(@PathParam("id") @NotNull int id) {
        try {
            VrstaINivoStudija vrstaINivoStudija = vinsl.getById(id);

            if (vrstaINivoStudija == null) {
                return Response.noContent().build();
            }

            return Response.ok(Mapper.toVrstaINivoStudijaDTO(vrstaINivoStudija)).build();

        } catch (Exception e) {
            return Response.serverError().type(MediaType.APPLICATION_JSON).build();
        }
    }

}
