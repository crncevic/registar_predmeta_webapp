/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.Korisnik;
import dto.KorisnikDTO;
import java.net.URI;
import java.util.List;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import logic.KorisnikLogic;

/**
 *
 * @author Petar
 */
@Path("korisnik")
public class KorisnikController {

    private KorisnikLogic kl;

    public KorisnikController() {
        kl = new KorisnikLogic();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        try {
            List<KorisnikDTO> korisniciDTO = kl.getAll();

            if (korisniciDTO == null || korisniciDTO.isEmpty()) {
                return Response.noContent().build();
            }

            return Response.ok(korisniciDTO).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") @NotNull int id) {
        try {
            KorisnikDTO korisnikDTO = kl.getById(id);

            if (korisnikDTO == null) {
                return Response.noContent().build();
            }

            return Response.ok(korisnikDTO).build();

        } catch (Exception e) {
            return Response.serverError().entity(e).build();
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(@NotNull Korisnik korisnik) {
        try {
            KorisnikDTO korisnikDTO = kl.create(korisnik);
            return Response.created(URI.create("api/korisnik")).entity(korisnikDTO).build();
        } catch (ConstraintViolationException cve) {
            return Response.status(Response.Status.BAD_REQUEST).entity(cve).build();
        } catch (Exception e) {
            return Response.serverError().entity(e).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") @NotNull int id, @NotNull Korisnik korisnik) {
        try {
            if (id != korisnik.getKorisnikId()) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

            KorisnikDTO korisnikDTO = kl.update(korisnik);

            return Response.ok(korisnikDTO).build();
        } catch (ConstraintViolationException cve) {
            return Response.status(Response.Status.BAD_REQUEST).entity(cve).build();
        } catch (Exception e) {
            return Response.serverError().entity(e).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") @NotNull int id) {
        try {

            KorisnikDTO korisnikDTO = kl.delete(id);
            return Response.ok(korisnikDTO).build();

        } catch (Exception e) {
            return Response.serverError().entity(e).build();
        }
    }

}
