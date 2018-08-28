/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.PredmetNaStudijskomProgramu;
import dto.PredmetNaStudijskomProgramuDTO;
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

}
