/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.Katedra;
import domain.Nastavnik;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import logic.KatedraLogic;
import logic.NastavnikLogic;

/**
 *
 * @author Petar
 */
@Path("katedra")
public class KatedraController {

    private KatedraLogic kl;

    public KatedraController() {
        kl = new KatedraLogic();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        try {
            List<Katedra> katedre = kl.getAll();

            if (katedre.size() == 0) {
                return Response.noContent().build();
            }

            return Response.ok(katedre).build();

        } catch (Exception ex) {
            return Response.serverError().build();
        }

    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") @NotNull int id) {
        try {
            Katedra katedra = kl.getById(id);

            if (katedra == null) {
                return Response.noContent().build();
            }

            return Response.ok(katedra).build();

        } catch (Exception e) {
            return Response.serverError().entity(e).build();
        }
    }

}
