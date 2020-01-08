package com.epam.rest;

import com.epam.models.Ad;
import com.epam.services.AdService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ads")
public class AdRest {

    AdService adService = new AdService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertAd(Ad ad) {

        return adService.insertAd(ad);

    }

    @GET
    @Path("/{adId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAd(@PathParam("adId") String id){

       return adService.getAd(id);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAds(){

        return adService.getAllAds();

    }

    @GET
    @Path("/person/{personId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllByPersonId(@PathParam("personId") int id){

        return adService.getAllByPersonId(id);

    }

    @DELETE
    @Path("/{adId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAd(@PathParam("adId") String id){

        return adService.deleteAd(id);

    }


}
