/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.OsobaUVeziSaUdzbenikom;
import dto.OsobaUVeziSaUdzbenikomDTO;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import logic.OsobaUVeziSaUdzbenikomLogic;

/**
 *
 * @author Petar
 */
@Path("osoba-udzbenik")
public class OsobaUVeziSaUdzbenikomController {

    private OsobaUVeziSaUdzbenikomLogic ouvsul;

    public OsobaUVeziSaUdzbenikomController() {
        ouvsul = new OsobaUVeziSaUdzbenikomLogic();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        try {
            List<OsobaUVeziSaUdzbenikomDTO> osobeDTO = ouvsul.getAll();

            if (osobeDTO == null || osobeDTO.isEmpty()) {
                return Response.noContent().build();
            }

            return Response.ok(osobeDTO).build();

        } catch (Exception e) {
            return Response.serverError().entity(e).build();
        }

    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") @NotNull int id) {
        try {
            OsobaUVeziSaUdzbenikomDTO osobaUVeziSaUdzbenikomDTO = ouvsul.getById(id);

            if (osobaUVeziSaUdzbenikomDTO == null) {
                return Response.noContent().build();
            }

            return Response.ok(osobaUVeziSaUdzbenikomDTO).build();

        } catch (Exception e) {
            return Response.serverError().entity(e).build();
        }
    }
    
    @GET
    @Path("/udzbenik/{udzbenikId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getOsobaByUdzbenikId(@PathParam("udzbenikId") @NotNull int udzbenikId) {
        try {
            List<OsobaUVeziSaUdzbenikomDTO> osobeUVeziSaUdzbenikomDTO = ouvsul.getOsobaByUdzbenikId(udzbenikId);

            if (osobeUVeziSaUdzbenikomDTO== null || osobeUVeziSaUdzbenikomDTO.isEmpty()) {
                return Response.noContent().build();
            }

            return Response.ok(osobeUVeziSaUdzbenikomDTO).build();

        } catch (Exception e) {
            return Response.serverError().entity(e).build();
        }
    }
    
   
            

}
