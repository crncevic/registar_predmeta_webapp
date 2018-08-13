/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.StudijskiProgram;
import dto.StudijskiProgramDTO;
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
import logic.StudijskiProgramLogic;
import mapper.Mapper;

/**
 *
 * @author Petar
 */
@Path("studijski-program")
public class StudijskiProgramController {

    @Inject
    private StudijskiProgramLogic spl;
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response getAll() {
        try {
            List<StudijskiProgram> studijskiProgrami = spl.getAll();

            if (studijskiProgrami == null || studijskiProgrami.isEmpty()) {
                return Response.noContent().build();
            }

            List<StudijskiProgramDTO> studijskiProgramiDTO = new ArrayList<>();

            for (StudijskiProgram sp : studijskiProgrami) {
                studijskiProgramiDTO.add(Mapper.toStudijskiProgramDTO(sp));
            }

            return Response.ok(studijskiProgramiDTO).build();
        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response getById(@PathParam("id") @NotNull int id) {
        try {
            StudijskiProgram studijskiProgram = spl.getById(id);

            if (studijskiProgram == null) {
                return Response.noContent().build();
            }

            return Response.ok(Mapper.toStudijskiProgramDTO(studijskiProgram)).build();

        } catch (Exception e) {
            return Response.serverError().type(MediaType.APPLICATION_JSON).build();
        }
    }

}
