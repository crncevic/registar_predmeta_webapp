/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.OsobaUVeziSaUdzbenikom;
import dto.OsobaUVeziSaUdzbenikomDTO;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import logic.OsobaUVeziSaUdzbenikomLogic;
import mapper.Mapper;

/**
 *
 * @author Petar
 */
@Path("osoba-udzbenik")
public class OsobaUVeziSaUdzbenikomController {

    private final OsobaUVeziSaUdzbenikomLogic ouvsul;

    public OsobaUVeziSaUdzbenikomController() {
        ouvsul = new OsobaUVeziSaUdzbenikomLogic();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public Response getAll() {
        try {
            List<OsobaUVeziSaUdzbenikom> osobe = ouvsul.getAll();

            if (osobe == null || osobe.isEmpty()) {
                return Response.noContent().build();
            }

            List<OsobaUVeziSaUdzbenikomDTO> osobeDTO = new ArrayList<>();

            for (OsobaUVeziSaUdzbenikom osobaUVeziSaUdzbenikom : osobe) {
                osobeDTO.add(Mapper.toOsobaUVeziSaUzbenikDTO(osobaUVeziSaUdzbenikom));
            }

            return Response.ok(osobeDTO).build();

        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }

    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public Response getById(@PathParam("id") @NotNull int id) {
        try {
            OsobaUVeziSaUdzbenikom osobaUVeziSaUdzbenikom = ouvsul.getById(id);

            if (osobaUVeziSaUdzbenikom == null) {
                return Response.noContent().build();
            }

            return Response.ok(Mapper.toOsobaUVeziSaUzbenikDTO(osobaUVeziSaUdzbenikom)).build();

        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

    @GET
    @Path("/udzbenik/{udzbenikId}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public Response getOsobaByUdzbenikId(@PathParam("udzbenikId") @NotNull int udzbenikId) {
        try {
            List<OsobaUVeziSaUdzbenikom> osobeUVeziSaUdzbenikom = ouvsul.getOsobaByUdzbenikId(udzbenikId);

            if (osobeUVeziSaUdzbenikom == null || osobeUVeziSaUdzbenikom.isEmpty()) {
                return Response.noContent().build();
            }

            List<OsobaUVeziSaUdzbenikomDTO> osobeDTO = new ArrayList<>();

            for (OsobaUVeziSaUdzbenikom osobaUVeziSaUdzbenikom : osobeUVeziSaUdzbenikom) {
                osobeDTO.add(Mapper.toOsobaUVeziSaUzbenikDTO(osobaUVeziSaUdzbenikom));
            }

            return Response.ok(osobeDTO).build();

        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

}
