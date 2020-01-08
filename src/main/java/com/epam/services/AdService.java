package com.epam.services;

import com.epam.dao.AdDao;
import com.epam.dao.PersonDao;
import com.epam.models.Ad;

import javax.ws.rs.core.Response;

public class AdService {

    AdDao dao = new AdDao();

    public Response insertAd(Ad ad) {
        PersonDao personDao = new PersonDao();

        ad.setPerson(personDao.getPerson(ad.getPerson_id()));

        boolean isInserted = dao.insertAd(ad);

        return isInserted ? Response.status(201).entity(ad).build() :
                Response.status(400).build();
    }

    public Response getAd(String id){

        if (dao.getAd(id) == null)

            return Response.status(404).build();

        return Response.status(200).entity(dao.getAd(id)).build();
    }

    public Response getAllAds() {

        if(dao.getAllAds().isEmpty()) return Response.status(404).build();

        return Response.status(200).entity(dao.getAllAds()).build();

    }

    public Response getAllByPersonId(int id) {

        if (dao.getAllByPersonId(id).isEmpty()) return Response.status(404).build();

        return Response.status(200).entity(dao.getAllByPersonId(id)).build();

    }

    public Response deleteAd(String id){

        boolean isDeleted = dao.deleteAd(id);

        return isDeleted ? Response.status(200).build() :
                Response.status(400).build();
    }


}
