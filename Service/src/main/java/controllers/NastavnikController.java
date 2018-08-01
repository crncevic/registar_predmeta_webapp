/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.Katedra;
import domain.Nastavnik;
import java.util.List;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import logic.NastavnikLogic;

/**
 *
 * @author Petar
 */
@Path("nastavnik")
public class NastavnikController {

 
    private NastavnikLogic nl;
    
    public NastavnikController(){
        nl = new NastavnikLogic();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        try {
            List<Nastavnik> nastavnici = nl.getAll();

            if (nastavnici.isEmpty()) {
                return Response.noContent().build();
            }

            return Response.ok(nastavnici).build();
        } catch (Exception e) {
            return Response.serverError().entity(e).build();
        }
    }
    
     @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") @NotNull int id) {
        try {
            Nastavnik nastavnik = nl.getById(id);

            if (nastavnik == null) {
                return Response.noContent().build();
            }

            return Response.ok(nastavnik).build();

        } catch (Exception e) {
            return Response.serverError().entity(e).build();
        }
    }
}
