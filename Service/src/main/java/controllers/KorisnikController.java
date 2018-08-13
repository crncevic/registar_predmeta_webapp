/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.Korisnik;
import dto.KorisnikDTO;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
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
import mapper.Mapper;

/**
 *
 * @author Petar
 */
@Path("korisnik")
public class KorisnikController {

    @Inject
    private KorisnikLogic kl;

   

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public Response getAll() {
        try {
            List<Korisnik> korisnici = kl.getAll();

            if (korisnici == null || korisnici.isEmpty()) {
                return Response.noContent().build();
            }

            List<KorisnikDTO> korisniciDTO = new ArrayList<>();

            for (Korisnik korisnik : korisnici) {
                korisniciDTO.add(Mapper.toKorisnikDTO(korisnik));
            }

            return Response.ok(korisniciDTO).build();
        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public Response getById(@PathParam("id") @NotNull int id) {
        try {
            Korisnik korisnik = kl.getById(id);

            if (korisnik == null) {
                return Response.noContent().build();
            }

            return Response.ok(Mapper.toKorisnikDTO(korisnik)).build();

        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public Response create(@NotNull KorisnikDTO korisnikDTO) {
        try {
            Korisnik korisnik = Mapper.toKorisnik(korisnikDTO);
            KorisnikDTO createdkorisnikDTO = Mapper.toKorisnikDTO(kl.create(korisnik));
            return Response.created(URI.create("api/korisnik")).entity(createdkorisnikDTO).build();
        } catch (ConstraintViolationException cve) {
            return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(cve).build();
        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public Response update(@PathParam("id") @NotNull int id, @NotNull KorisnikDTO korisnikDTO) {
        try {
            if (id != korisnikDTO.getKorisnikId()) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

            Korisnik korisnik = Mapper.toKorisnik(korisnikDTO);
            KorisnikDTO updatedKorisnikDTO = Mapper.toKorisnikDTO(kl.update(korisnik));

            return Response.ok(updatedKorisnikDTO).build();
        } catch (ConstraintViolationException cve) {
            return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(cve).build();
        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public Response delete(@PathParam("id") @NotNull int id) {
        try {

            KorisnikDTO korisnikDTO = Mapper.toKorisnikDTO(kl.delete(id));
            return Response.ok(korisnikDTO).build();

        } catch (Exception e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e).build();
        }
    }

}
